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

    public ViewLoyaltyDefinitionDto loyaltyDefinitionToViewLoyaltyDefinitionDto(LoyaltyDefinition loyaltyDefinition){
        return ViewLoyaltyDefinitionDto.builder()
                .id(loyaltyDefinition.getId().toString())
                .type(loyaltyDefinition.getLoyaltyType().name())
                .freeDrinkId(checkIfFreeDrinkExist(loyaltyDefinition.getFreeDrink(),false))
                .freeDrinkName(checkIfFreeDrinkExist(loyaltyDefinition.getFreeDrink(),true))
                .restaurantId(loyaltyDefinition.getRestaurant().getId().toString())
                .discountInPercentage(loyaltyDefinition.getDiscountInPercentage())
                .reset(loyaltyDefinition.isReset())
                .threshold(loyaltyDefinition.getThreshold())
                .build();
    }

    private String checkIfFreeDrinkExist(Food freeDrink,Boolean name){
        if(freeDrink == null){
            return "";
        }else{
            if(name.equals(true)){
                return freeDrink.getName();
            }else{
                return freeDrink.getId().toString();
            }

        }
    }
}
