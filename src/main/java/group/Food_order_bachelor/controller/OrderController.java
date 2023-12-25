package group.Food_order_bachelor.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.dto.order.*;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.enums.Payment_type;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.loyaltyService.LoyaltyService;
import group.Food_order_bachelor.service.openRouteService.OpenRouteService;
import group.Food_order_bachelor.service.orderService.OrderService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final FoodService foodService;
    private final LoyaltyService loyaltyService;
    private final OpenRouteService openRouteService;

    @PostMapping("/create-order")
    @CrossOrigin("http://localhost:3000")
    public String createOrder(@RequestBody CreateOrderDto dto){
        var foods = foodService.getFoodsByIds(dto.getFoodIds());
        var user = userService.getUserById(UUID.fromString(dto.getUserId()));
        var restaurant = foods.iterator().next().getMenu().getRestaurant();
        var loyalties = loyaltyService.findLoyaltiesForUser(user, restaurant);
        orderService.createOrder(dto,userService.getUserById(UUID.fromString(dto.getUserId()))
                ,foodService.getFoodsByIds(dto.getFoodIds()),loyalties,restaurant);
        loyaltyService.incrementLoyalties(user,restaurant);
        if(dto.getPaymentType().equals(Payment_type.ONLINE.name())){
            try{
                PaymentIntentCreateParams createParams = new
                        PaymentIntentCreateParams.Builder()
                        .setCurrency("usd")
                        .putMetadata("featureRequest", dto.getNote())
                        .setAmount(420*100L)
                        .build();

                PaymentIntent intent = PaymentIntent.create(createParams);
                return intent.getClientSecret();
            }catch (StripeException e){
                throw new RuntimeException(e.getMessage());
            }
        }else{
            return "cash";
        }
    }

    @PatchMapping("/take-order")
    @CrossOrigin("http://localhost:3000")
    public void takeOrder(@RequestBody DelivererTakeOrderDto dto){//provera da li ima slotova proveriti na frontu
        orderService.setDelivererToOrder(userService.getUserById(UUID.fromString(dto.getDelivererId())),dto.getOrderId());
        userService.lowerSlotsForDeliverer(dto.getDelivererId());
    }

    @PatchMapping("/cancel-order-for-deliverer")
    @CrossOrigin("http://localhost:3000")
    public void cancelOrderForDeliverer(@RequestBody DelivererCancelOrderDto dto){
        orderService.removerDelivererFromOrder(dto.getOrderId());
        userService.increaseAvailableSlotsForDeliverer(dto.getDelivererId());
    }

    @PostMapping("/start-all-deliveries")
    @CrossOrigin("http://localhost:3000")
    public void startAllDeliveries(@RequestBody StartAllDeliveriesDto dto){
        orderService.startAllDeliveries(
                openRouteService.getRoute(
                        Coordinates.builder().lat(dto.getLatitudeStart()).lng(dto.getLongitudeStart()).build(),
                        Coordinates.builder().lat(dto.getLatitudeFinish()).lng(dto.getLongitudeFinish()).build())
                        , dto);
    }

    @PostMapping("/start-delivery")
    @CrossOrigin("http://localhost:3000")
    public void startDelivery(@RequestBody StartDeliveryDto dto){
        orderService.startDelivery(openRouteService.getRoute(
                Coordinates.builder().lat(dto.getLatitudeStart()).lng(dto.getLongitudeStart()).build(),
                Coordinates.builder().lat(dto.getLatitudeFinish()).lng(dto.getLongitudeFinish()).build())
                ,dto.getDelivererId());
    }

    @PostMapping("/finish-delivery")
    @CrossOrigin("http://localhost:3000")
    public void finishDelivery(@RequestParam String orderId){
        orderService.finishDelivery(orderId);
    }

    @PatchMapping("/cancel-order-customer")
    @CrossOrigin("http://localhost:3000")
    public void cancelOrderForCustomer(@RequestParam String orderId){
        orderService.cancelOrderForCustomer(orderId);
    }

    @GetMapping("/get-orders-for-customer-initial")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForCustomerInitial(@RequestBody GetOrdersForCustomerDto dto){
        return orderService.viewOrdersForCustomerInitialState(dto);
    }

    @GetMapping("/get-orders-for-customer-history")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForCustomerHistory(@RequestBody GetOrdersForCustomerDto dto){
        return orderService.viewOrdersForCustomerHistory(dto);
    }

    @GetMapping("/get-orders-for-deliverer-initial")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForDelivererInitial(@RequestParam String restaurantId){
        return orderService.getOrdersForDelivererInitial(restaurantId);
    }

    @GetMapping("/get-orders-for-deliverer-taken")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForDelivererTaken(@RequestParam String delivererId){
        return orderService.getOrdersForDelivererTaken(delivererId);
    }

    @GetMapping("/get-orders-for-deliverer-in-delivery")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForDelivererInDelivery(@RequestParam String delivererId){
        return orderService.getOrdersForDelivererInDelivery(delivererId);
    }

    @GetMapping("/get-orders-for-deliverer-history")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForDelivererHistory(@RequestParam String delivererId){
        return orderService.getOrdersForDelivererHistory(delivererId);
    }

    @GetMapping("/get-orders-for-restaurant-history")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrderDto> getOrdersForRestaurantHistory(@RequestParam String restaurantId){
        return orderService.getOrdersForRestaurantHistory(restaurantId);
    }
}
