package group.Food_order_bachelor.dto.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodDto {
    private String name;
    private String description;
    private List<String> ingredients;
    private int estimatedTime;
    private String foodType;
    private boolean meatFree;
    private double price;
    private String menuId;
}
