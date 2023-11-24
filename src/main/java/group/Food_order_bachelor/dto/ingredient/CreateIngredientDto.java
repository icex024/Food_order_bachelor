package group.Food_order_bachelor.dto.ingredient;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateIngredientDto {
    private String name;
    private boolean meatFree;
    private List<String> allergenIds;
}
