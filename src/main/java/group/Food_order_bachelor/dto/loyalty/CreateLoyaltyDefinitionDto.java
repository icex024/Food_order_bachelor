package group.Food_order_bachelor.dto.loyalty;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoyaltyDefinitionDto {
    private String restaurantId;
    private int threshold;
    private boolean reset;
    private int discountInPercentage;
    private String freeDrink;
}
