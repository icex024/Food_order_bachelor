package group.Food_order_bachelor.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import group.Food_order_bachelor.dto.order.CreateOrderDto;
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
                ,foodService.getFoodsByIds(dto.getFoodIds()),loyalties);
        loyaltyService.incrementLoyalties(user,restaurant);
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
    }

    @GetMapping("/get-orders-for-customer")
    @CrossOrigin("http://localhost:3000")
    public void getOrdersForCustomer(@RequestParam String id){

    }

    @GetMapping("/get-orders-for-driver")
    @CrossOrigin("http://localhost:3000")
    public void getOrdersForDriver(@RequestParam String id){

    }
}
