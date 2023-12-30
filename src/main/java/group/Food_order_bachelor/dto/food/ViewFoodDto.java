package group.Food_order_bachelor.dto.food;

import lombok.*;
import org.springframework.core.io.Resource;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewFoodDto {
    private String id;
    private String name;
    private String description;
    private List<String> ingredients;
    private int estimatedTime;
    private String foodType;
    private boolean meatFree;
    private double price;
    private String menuId;
    private Resource image;
}
