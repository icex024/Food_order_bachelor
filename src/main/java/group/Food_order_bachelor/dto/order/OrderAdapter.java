package group.Food_order_bachelor.dto.order;

import group.Food_order_bachelor.enums.Payment_type;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Order;
import group.Food_order_bachelor.model.User;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class OrderAdapter {
    public Order createOrderDtoToOrder(CreateOrderDto dto, User user, List<Food> foods){
        return Order.builder().id(UUID.randomUUID()).timeOfMakingOrder(LocalDateTime.now()).note(dto.getNote())
                .paymentType(Payment_type.valueOf(dto.getPaymentType())).foods(foods).user(user).build();
    }
}
