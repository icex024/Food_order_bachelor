package group.Food_order_bachelor.service.ingredientService;

import group.Food_order_bachelor.dto.ingredient.CreateIngredientDto;
import group.Food_order_bachelor.dto.ingredient.IngredientToShowDto;
import group.Food_order_bachelor.model.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientServiceInterface {
    void CreateNewIngredient(CreateIngredientDto dto);
    List<IngredientToShowDto> getAllIngredients();
    Set<Ingredient> getIngredientsByIds(List<String> ids);
    void deleteIngredient(String ingredientId);
}
