package group.Food_order_bachelor.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import group.Food_order_bachelor.dto.order.CreateOrderDto;
import group.Food_order_bachelor.dto.order.DelivererCancelOrderDto;
import group.Food_order_bachelor.dto.order.DelivererTakeOrderDto;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.enums.Payment_type;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.loyaltyService.LoyaltyService;
import group.Food_order_bachelor.service.orderService.OrderService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final FoodService foodService;
    private final LoyaltyService loyaltyService;

    @PostMapping("/create-order")
    @CrossOrigin("http://localhost:3000")
    public String createOrder(@RequestBody CreateOrderDto dto){
        //nadji loyalty
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

    @PatchMapping("/cancel-order-customer")
    @CrossOrigin("http://localhost:3000")
    public void cancelOrderForCustomer(@RequestParam String orderId){

    }

    @GetMapping("/get-orders-for-customer")
    @CrossOrigin("http://localhost:3000")
    public void getOrdersForCustomer(@RequestParam String id){

    }
}
