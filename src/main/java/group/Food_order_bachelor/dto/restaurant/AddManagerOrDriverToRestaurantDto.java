package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddManagerOrDriverToRestaurantDto {
    private String userId;
    private String restaurantId;
}
