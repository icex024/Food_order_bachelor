package group.Food_order_bachelor.service.orderService;

import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.dto.order.*;
import group.Food_order_bachelor.enums.Loyalty_type;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.*;
import group.Food_order_bachelor.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;
    private final OrderAdapter orderAdapter = new OrderAdapter();
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;
    private final String topicExchangeName = "bachelor-exchange";

    @Override
    public void createOrder(CreateOrderDto dto, User user, List<Food> foods, Set<Loyalty> loyalties,Restaurant restaurant) {
        var order = orderAdapter.createOrderDtoToOrder(dto,user,addFreeDrinks(foods,loyalties));
        order.setPrice(calculateOrderPrice(foods,loyalties));
        order.setEstimatedTime(calculateEstimatedTime(foods));
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
        changeOrderStatus(orderId,Order_status.TAKEN_BY_DELIVERER);
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

    @Override
    public void startDelivery(List<Coordinates> route, String delivererId) {
        sendRoute(delivererId,route);
    }

    @Override
    public void startAllDeliveries(List<Coordinates> route, StartAllDeliveriesDto dto) {
        sendRoute(dto.getDelivererId(),route);
        for(var orderId:dto.getOrderIds()){
            changeOrderStatus(orderId,Order_status.IN_DELIVERY);
        }
    }

    @Override
    public void finishDelivery(String orderId) {
        changeOrderStatus(orderId,Order_status.DELIVERED);
    }

    @Override
    public List<ViewOrderDto> viewOrdersForCustomerInitialState(GetOrdersForCustomerDto dto) {
        return getOrdersForView(orderRepository.getOrdersByCustomerAndRestaurantInitialState(
                UUID.fromString(dto.getCustomerId())
                ,UUID.fromString(dto.getRestaurantId())));
    }

    @Override
    public List<ViewOrderDto> viewOrdersForCustomerHistory(GetOrdersForCustomerDto dto) {
        return getOrdersForView(orderRepository.getOrdersByCustomerAndRestaurantHistory(
                UUID.fromString(dto.getCustomerId())
                ,UUID.fromString(dto.getRestaurantId())));
    }

    @Override
    public List<ViewOrderDto> getOrdersForDelivererInitial(String restaurantId) {
        return getOrdersForView(orderRepository.getOrdersForDriverInitial(UUID.fromString(restaurantId)));
    }

    @Override
    public List<ViewOrderDto> getOrdersForDelivererTaken(String delivererId) {
        return getOrdersForView(orderRepository.getOrdersForDelivererTaken(UUID.fromString(delivererId)));
    }

    @Override
    public List<ViewOrderDto> getOrdersForDelivererInDelivery(String delivererId) {
        return getOrdersForView(orderRepository.getOrdersForDelivererInDelivery(UUID.fromString(delivererId)));
    }

    @Override
    public List<ViewOrderDto> getOrdersForDelivererHistory(String delivererId) {
        return getOrdersForView(orderRepository.getOrdersByForDelivererHistory(UUID.fromString(delivererId)));
    }

    @Override
    public List<ViewOrderDto> getOrdersForRestaurantHistory(String restaurantId) {
        return getOrdersForView(orderRepository.getOrdersForRestaurantHistory(UUID.fromString(restaurantId)));
    }

    private List<ViewOrderDto> getOrdersForView(Set<Order> orders){
        List<ViewOrderDto> list = new ArrayList<>();
        for (var order : orders){
            list.add(orderAdapter.orderToViewOrderDto(order));
        }
        return list;
    }

    private void sendRoute(String delivererId,List<Coordinates> route){
        Queue queue= new Queue(delivererId,false);
        TopicExchange topicExchange = new TopicExchange(topicExchangeName);
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with("delivery.#");
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(topicExchange);
        amqpAdmin.declareBinding(binding);
        ScheduledExecutorService executorService = newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(()->{
            var r = route.remove(0);
            rabbitTemplate.convertAndSend(topicExchangeName,"delivery.service",r);
            },0,7, TimeUnit.SECONDS);
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
