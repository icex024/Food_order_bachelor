package group.Food_order_bachelor.service.orderService;

import group.Food_order_bachelor.dto.order.CreateOrderDto;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Loyalty;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;

import java.util.List;
import java.util.Set;

public interface OrderServiceInterface {
    void createOrder(CreateOrderDto dto, User user, List<Food> foods, Set<Loyalty> loyalties, Restaurant restaurant);
    void setDelivererToOrder(User deliverer,String orderId);
    void removerDelivererFromOrder(String orderId);
    void cancelOrderForCustomer(String orderId);
}
