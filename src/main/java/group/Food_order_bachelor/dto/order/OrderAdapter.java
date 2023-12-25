package group.Food_order_bachelor.dto.order;

import group.Food_order_bachelor.enums.Payment_type;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Order;
import group.Food_order_bachelor.model.User;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class OrderAdapter {
    public Order createOrderDtoToOrder(CreateOrderDto dto, User user, List<Food> foods){
        return Order
                .builder()
                .id(UUID.randomUUID())
                .timeOfMakingOrder(LocalDateTime.now())
                .note(dto.getNote())
                .paymentType(Payment_type.valueOf(dto.getPaymentType()))
                .foods(foods)
                .user(user)
                .build();
    }

    public ViewOrderDto orderToViewOrderDto(Order order){
        return ViewOrderDto
                .builder()
                .id(order.getId().toString())
                .customerId(order.getUser().getId().toString())
                .restaurantId(order.getRestaurant().getId().toString())
                .foodIds(getFoodIds(order.getFoods()))
                .price(order.getPrice())
                .note(order.getNote())
                .paymentType(order.getPaymentType().toString())
                .timeOfMakingOrder(order.getTimeOfMakingOrder().toString())
                .estimatedTime(order.getEstimatedTime())
                .status(order.getStatus())
                .delivererId(setDelivererId(order.getDeliverer()))
                .build();
    }

    private String setDelivererId(User deliverer){
        if(deliverer == null){
            return "";
        }else{
            return deliverer.getId().toString();
        }
    }

    private List<String> getFoodIds(List<Food> foods){
        List<String> ids = new ArrayList<>();
        for(var food : foods){
            ids.add(food.getId().toString());
        }
        return ids;
    }
}
