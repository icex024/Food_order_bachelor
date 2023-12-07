package group.Food_order_bachelor.dto.menu;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuDto {
    private String name;
    private String restaurantId;
}
