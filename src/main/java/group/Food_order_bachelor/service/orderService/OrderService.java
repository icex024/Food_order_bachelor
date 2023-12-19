package group.Food_order_bachelor.service.orderService;

import group.Food_order_bachelor.dto.order.CreateOrderDto;
import group.Food_order_bachelor.dto.order.OrderAdapter;
import group.Food_order_bachelor.enums.Loyalty_type;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.*;
import group.Food_order_bachelor.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;
    private final OrderAdapter orderAdapter = new OrderAdapter();

    @Override
    public void createOrder(CreateOrderDto dto, User user, List<Food> foods, Set<Loyalty> loyalties,Restaurant restaurant) {
        var order = orderAdapter.createOrderDtoToOrder(dto,user,addFreeDrinks(foods,loyalties));
        order.setPrice(calculateOrderPrice(foods,loyalties));
        order.setEstimatedTime(calculateEstimatedTime(foods));
//        order.setEstimatedTime(2);
        order.setStatus(Order_status.PROCESS.name());
        order.setRestaurant(restaurant);
        orderRepository.save(order);
        setTimer(order,orderRepository);
    }

    @Override
    public void setDelivererToOrder(User deliverer,String orderId) {
        var order = orderRepository.getReferenceById(UUID.fromString(orderId));
        order.setDeliverer(deliverer);
        orderRepository.saveAndFlush(order);
        changeOrderStatus(orderId,Order_status.DELIVERY);
    }

    @Override
    public void removerDelivererFromOrder(String orderId) {
        var order = orderRepository.getReferenceById(UUID.fromString(orderId));
        order.setDeliverer(null);
        orderRepository.saveAndFlush(order);
        changeOrderStatus(orderId,Order_status.READY);
    }

    @Override
    public void cancelOrderForCustomer(String orderId) {
        changeOrderStatus(orderId,Order_status.CANCELLED);
    }


    private void changeOrderStatus(String orderId, Order_status status) {
        var order = orderRepository.getReferenceById(UUID.fromString(orderId));
        order.setStatus(status.toString());
        orderRepository.saveAndFlush(order);
    }

    private void setTimer(Order order,OrderRepository orderRepository){
        Date endDate = Date.from(order.getTimeOfMakingOrder().plusMinutes(order.getEstimatedTime()).atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(endDate);
        new Timer().schedule(new ChangeOrderStatus(order,orderRepository),endDate);
    }

    private List<Food> addFreeDrinks(List<Food> foods,Set<Loyalty> loyalties){
        var loyaltyDefinitions = foods.iterator().next().getMenu().getRestaurant().getLoyaltyDefinitions();
        for(var loyaltyDef:loyaltyDefinitions){
            if(loyaltyDef.getLoyaltyType().equals(Loyalty_type.FREE_DRINK)){
                for(var loyalty:loyalties ){
                    if(loyalty.getLoyaltyDefinition().getId().equals(loyaltyDef.getId())
                            && loyalty.getNumberOfOrders() == loyaltyDef.getThreshold() ){
                        foods.add(loyaltyDef.getFreeDrink());
                    }
                }
            }
        }
        return foods;
    }

    private double calculateOrderPrice(List<Food> foods,Set<Loyalty> loyalties){
        double price = 0.0;
        var loyaltyDefinitions = foods.iterator().next().getMenu().getRestaurant().getLoyaltyDefinitions();
        for(var food : foods){
            price = price + food.getPrice();
        }
        for(var loyaltyDef:loyaltyDefinitions){
            if(loyaltyDef.getLoyaltyType().equals(Loyalty_type.DISCOUNT)){
                for(var loyalty:loyalties ){
                    if(loyalty.getLoyaltyDefinition().getId().equals(loyaltyDef.getId())
                            && loyalty.getNumberOfOrders() == loyaltyDef.getThreshold() ){
                        price = price*((100.0-loyaltyDef.getDiscountInPercentage())/100);
                    }
                }
            }
        }
        return price;
    }

    private int calculateEstimatedTime(List<Food> foods){
        int estimatedTime = 0;
        for(var food : foods){
            estimatedTime = estimatedTime + food.getEstimatedTimeForPreparationInMinutes();
        }
        return estimatedTime;
    }
}

@AllArgsConstructor
class ChangeOrderStatus extends TimerTask{
    private Order order;
    private final OrderRepository orderRepository;
    @Override
    public void run() {
        order.setStatus(Order_status.READY.name());
        orderRepository.saveAndFlush(order);
    }
}
