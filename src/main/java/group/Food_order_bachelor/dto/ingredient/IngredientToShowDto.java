package group.Food_order_bachelor.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientToShowDto {
    private String id;
    private String name;
    private List<String> allergensId;
}
