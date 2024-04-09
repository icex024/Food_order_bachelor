package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditRestaurantStatusDto {
    private String id;
    private boolean visibility;
}
