package group.Food_order_bachelor.service.loyaltyService;

import group.Food_order_bachelor.dto.loyalty.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.dto.loyalty.LoyaltyAdapter;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.LoyaltyDefinition;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.repository.LoyaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoyaltyService implements LoyaltyServiceInterface {
    private final LoyaltyRepository loyaltyRepository;
    private final LoyaltyAdapter loyaltyAdapter =new LoyaltyAdapter();
    @Override
    public void creteLoyalty(CreateLoyaltyDefinitionDto dto, Restaurant restaurant, Food food) {
        LoyaltyDefinition loyalty;
        if(food == null){
            loyalty = loyaltyAdapter.createLoyaltyDtoToLoyaltyDiscount(dto,restaurant);
        }else{
            loyalty = loyaltyAdapter.createLoyaltyDtoToLoyaltyFreeDrink(dto,restaurant,food);
        }
        loyaltyRepository.save(loyalty);
    }
}
