package group.Food_order_bachelor.dto.food;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddOrChangeFoodFromMenuDto {
    private String menuId;
    private String foodId;
}
