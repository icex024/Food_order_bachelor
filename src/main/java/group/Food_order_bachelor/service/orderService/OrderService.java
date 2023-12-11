package group.Food_order_bachelor.service.orderService;

import group.Food_order_bachelor.dto.order.CreateOrderDto;
import group.Food_order_bachelor.dto.order.OrderAdapter;
import group.Food_order_bachelor.enums.Loyalty_type;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Loyalty;
import group.Food_order_bachelor.model.LoyaltyDefinition;
import group.Food_order_bachelor.model.User;
import group.Food_order_bachelor.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;
    private final OrderAdapter orderAdapter = new OrderAdapter();

    @Override
    public void createOrder(CreateOrderDto dto, User user, List<Food> foods, Set<Loyalty> loyalties) {
        var order = orderAdapter.createOrderDtoToOrder(dto,user,addFreeDrinks(foods,loyalties));
        order.setPrice(calculateOrderPrice(foods,loyalties));
        order.setEstimatedTime(calculateEstimatedTime(foods));
        order.setStatus(Order_status.PROCESS.name());
        orderRepository.save(order);
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
