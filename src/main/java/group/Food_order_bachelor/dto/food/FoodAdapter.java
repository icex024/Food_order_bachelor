package group.Food_order_bachelor.dto.food;

import group.Food_order_bachelor.enums.Food_type;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Ingredient;
import group.Food_order_bachelor.model.Menu;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class FoodAdapter {
    public Food createFoodDtoToFood(CreateFoodDto dto, Menu menu, Set<Ingredient> ingredients){
        return Food.builder().id(UUID.randomUUID()).description(dto.getDescription()).name(dto.getName())
                .estimatedTimeForPreparationInMinutes(dto.getEstimatedTime()).price(dto.getPrice()).
                foodType(Food_type.valueOf(dto.getFoodType())).ingredients(ingredients).menu(menu).
                meatFree(dto.isMeatFree()).build();
    }

    public ViewFoodDto foodToViewFoodDto(Food food){
        List<String> ingredients = new ArrayList<>();
        for (var ingredient:
             food.getIngredients()) {
            ingredients.add(ingredient.getId().toString());
        }
        return ViewFoodDto.builder()
                .id(food.getId().toString())
                .name(food.getName())
                .description(food.getDescription())
                .estimatedTime(food.getEstimatedTimeForPreparationInMinutes())
                .foodType(food.getFoodType().name())
                .menuId(food.getMenu().getId().toString())
                .meatFree(food.isMeatFree())
                .price(food.getPrice())
                .ingredients(ingredients)
                .image(new ByteArrayResource(food.getImageFood().getData()))
                .build();
    }

}
