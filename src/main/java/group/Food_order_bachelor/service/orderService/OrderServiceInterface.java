package group.Food_order_bachelor.service.orderService;

import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.dto.order.*;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.*;

import java.util.List;
import java.util.Set;

public interface OrderServiceInterface {
    void createOrder(CreateOrderDto dto, User user, List<Food> foods, Set<Loyalty> loyalties, Restaurant restaurant);
    void setDelivererToOrder(User deliverer,String orderId);
    void removerDelivererFromOrder(String orderId);
    void cancelOrderForCustomer(String orderId);
    void startDelivery(List<Coordinates> route, String delivererId);
    void startAllDeliveries(List<Coordinates> route, StartAllDeliveriesDto dto);
    void finishDelivery(String orderId);
    List<ViewOrderDto> viewOrdersForCustomerInitialState(GetOrdersForCustomerDto dto);
    List<ViewOrderDto> viewOrdersForCustomerHistory(GetOrdersForCustomerDto dto);
    List<ViewOrderDto> getOrdersForDelivererInitial(String restaurantId);
    List<ViewOrderDto> getOrdersForDelivererTaken(String delivererId);
    List<ViewOrderDto> getOrdersForDelivererInDelivery(String delivererId);
    List<ViewOrderDto> getOrdersForDelivererHistory(String delivererId);
    List<ViewOrderDto> getOrdersForRestaurantHistory(String restaurantId);
}
