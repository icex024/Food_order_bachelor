package group.Food_order_bachelor.dto.loyaltyDefinition;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewLoyaltyDefinitionDto {
    private String id;
    private String restaurantId;
    private int threshold;
    private boolean reset;
    private String type;
    private int discountInPercentage;
    private String freeDrinkName;
    private String freeDrinkId;
}
