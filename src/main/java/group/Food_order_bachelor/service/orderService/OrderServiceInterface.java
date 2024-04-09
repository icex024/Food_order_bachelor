package group.Food_order_bachelor.service.orderService;

import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.dto.food.FoodStatisticsDto;
import group.Food_order_bachelor.dto.order.*;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface OrderServiceInterface {
    void createOrder(CreateOrderDto dto, User user, List<Food> foods, Set<Loyalty> loyalties, Restaurant restaurant);
    void setDelivererToOrder(User deliverer,String orderId);
    void removerDelivererFromOrder(String orderId);
    void cancelOrderForCustomer(String orderId);
    void startDelivery(List<Coordinates> route, String delivererId);
    void startAllDeliveries( StartAllDeliveriesDto dto);
    void finishDelivery(String orderId);
    List<ViewOrderDto> viewOrdersForCustomerInitialState(String customerId);
    List<ViewOrderDto> viewOrdersForCustomerHistory(String customerId);
    List<ViewOrderDto> getOrdersForDelivererInitial(String restaurantId);
    List<ViewOrderDto> getOrdersForDelivererTaken(String delivererId);
    List<ViewOrderDto> getOrdersForDelivererInDelivery(String delivererId);
    List<ViewOrderDto> getOrdersForDelivererHistory(String delivererId);
    List<ViewOrderDto> getOrdersForRestaurantHistory(String restaurantId);
    List<FoodStatisticsDto> setProperValuesForFoodStatisticsDto(List<FoodStatisticsDto> dtos, UUID restaurantId, LocalDateTime date);
}
