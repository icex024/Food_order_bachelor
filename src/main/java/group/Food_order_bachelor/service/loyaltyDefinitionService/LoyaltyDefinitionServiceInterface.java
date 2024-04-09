package group.Food_order_bachelor.service.loyaltyDefinitionService;

import group.Food_order_bachelor.dto.loyaltyDefinition.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.dto.loyaltyDefinition.ViewLoyaltyDefinitionDto;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Restaurant;

import java.util.List;
import java.util.UUID;

public interface LoyaltyDefinitionServiceInterface {
    void creteLoyalty(CreateLoyaltyDefinitionDto dto, Restaurant restaurant, Food food);
    List<ViewLoyaltyDefinitionDto> getLoyalties(UUID restaurantId);
}
