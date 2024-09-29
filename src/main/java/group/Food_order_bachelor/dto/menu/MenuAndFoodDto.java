package group.Food_order_bachelor.dto.menu;

import group.Food_order_bachelor.dto.food.ViewFoodDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuAndFoodDto {
    private String id;
    private String name;
    private List<ViewFoodDto> foods;
}
