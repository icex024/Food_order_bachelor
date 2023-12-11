package group.Food_order_bachelor.dto.loyaltyDefinition;

import group.Food_order_bachelor.enums.Loyalty_type;
import group.Food_order_bachelor.model.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class LoyaltyDefinitionAdapter {
    public LoyaltyDefinition createLoyaltyDtoToLoyaltyFreeDrink(CreateLoyaltyDefinitionDto dto, Restaurant restaurant,
    Food freeDrink){
            return LoyaltyDefinition.builder().id(UUID.randomUUID()).restaurant(restaurant).reset(dto.isReset())
                    .threshold(dto.getThreshold()).freeDrink(freeDrink).loyaltyType(Loyalty_type.FREE_DRINK).build();
    }

    public LoyaltyDefinition createLoyaltyDtoToLoyaltyDiscount(CreateLoyaltyDefinitionDto dto, Restaurant restaurant){
        return LoyaltyDefinition.builder().id(UUID.randomUUID()).restaurant(restaurant).reset(dto.isReset())
                .threshold(dto.getThreshold()).discountInPercentage(dto.getDiscountInPercentage())
                .loyaltyType(Loyalty_type.DISCOUNT).build();
    }

}
