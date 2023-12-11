package group.Food_order_bachelor.service.loyaltyDefinitionService;

import group.Food_order_bachelor.dto.loyaltyDefinition.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Restaurant;

public interface LoyaltyDefinitionServiceInterface {
    void creteLoyalty(CreateLoyaltyDefinitionDto dto, Restaurant restaurant, Food food);
}
