package group.Food_order_bachelor.service.loyaltyService;

import group.Food_order_bachelor.dto.loyalty.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Restaurant;

public interface LoyaltyServiceInterface {
    void creteLoyalty(CreateLoyaltyDefinitionDto dto, Restaurant restaurant, Food food);
}
