package group.Food_order_bachelor.service.loyaltyDefinitionService;

import group.Food_order_bachelor.dto.loyaltyDefinition.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.dto.loyaltyDefinition.LoyaltyDefinitionAdapter;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.LoyaltyDefinition;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.repository.LoyaltyDefinitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoyaltyDefinitionService implements LoyaltyDefinitionServiceInterface {
    private final LoyaltyDefinitionRepository loyaltyDefinitionRepository;
    private final LoyaltyDefinitionAdapter loyaltyAdapter =new LoyaltyDefinitionAdapter();
    @Override
    public void creteLoyalty(CreateLoyaltyDefinitionDto dto, Restaurant restaurant, Food food) {
        LoyaltyDefinition loyalty;
        if(food == null){
            loyalty = loyaltyAdapter.createLoyaltyDtoToLoyaltyDiscount(dto,restaurant);
        }else{
            loyalty = loyaltyAdapter.createLoyaltyDtoToLoyaltyFreeDrink(dto,restaurant,food);
        }
        loyaltyDefinitionRepository.save(loyalty);
    }
}
