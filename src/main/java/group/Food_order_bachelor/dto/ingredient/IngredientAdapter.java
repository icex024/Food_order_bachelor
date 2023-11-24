package group.Food_order_bachelor.dto.ingredient;


import group.Food_order_bachelor.model.Allergen;
import group.Food_order_bachelor.model.Ingredient;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class IngredientAdapter {
    public Ingredient ingredientDtoToIngredient(CreateIngredientDto dto, Set<Allergen> allergens){
        return Ingredient.builder().id(UUID.randomUUID()).name(dto.getName()).meatFree(dto.isMeatFree())
                .allergens(allergens).build();
    }
}
