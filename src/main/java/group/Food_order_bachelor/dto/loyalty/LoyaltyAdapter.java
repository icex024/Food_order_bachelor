package group.Food_order_bachelor.dto.loyalty;

import group.Food_order_bachelor.model.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class LoyaltyAdapter {
    public LoyaltyFreeDrink createLoyaltyDtoToLoyaltyFreeDrink(CreateLoyaltyDefinitionDto dto, Restaurant restaurant,
    Food freeDrink){
            return LoyaltyFreeDrink.builder().id(UUID.randomUUID()).restaurant(restaurant).reset(dto.isReset())
                    .threshold(dto.getThreshold()).freeDrink(freeDrink).build();
    }

    public LoyaltyDiscount createLoyaltyDtoToLoyaltyDiscount(CreateLoyaltyDefinitionDto dto, Restaurant restaurant){
        return LoyaltyDiscount.builder().id(UUID.randomUUID()).restaurant(restaurant).reset(dto.isReset())
                .threshold(dto.getThreshold()).discountInPercentage(dto.getDiscountInPercentage()).build();
    }

}
