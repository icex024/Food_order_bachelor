package group.Food_order_bachelor.dto.food;

import group.Food_order_bachelor.enums.Food_type;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Image;
import group.Food_order_bachelor.model.Ingredient;
import group.Food_order_bachelor.model.Menu;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import javax.swing.text.View;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class FoodAdapter {
    public Food createFoodDtoToFood(CreateFoodDto dto, Menu menu, Set<Ingredient> ingredients,Image image){
        return Food.builder()
                .id(UUID.randomUUID())
                .description(dto.getDescription())
                .name(dto.getName())
                .estimatedTimeForPreparationInMinutes(dto.getEstimatedTime())
                .price(dto.getPrice())
                .foodType(Food_type.valueOf(dto.getFoodType()))
                .ingredients(ingredients)
                .menu(menu)
                .meatFree(dto.isMeatFree())
                .imageFood(image)
                .build();
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
                .image(checkImage(food.getImageFood()))
                .build();
    }

    public FoodStatisticsDto foodToFoodStatisticsDto(Food food,String date){
        return FoodStatisticsDto.builder()
                .id(food.getId().toString())
                .date(date)
                .name(food.getName())
                .numberOfOrders(0)
                .build();
    }

    public FoodForLoyaltyDto foodToFoodForLoyaltyDto(Food food){
        return FoodForLoyaltyDto.builder()
                .id(food.getId().toString())
                .name(food.getName())
                .build();
    }

    private byte[] checkImage(Image image){
        if(image == null){
            return null;
        }else{
//            ByteArrayInputStream stream = new ByteArrayInputStream(image.getData());
            return image.getData();
        }
    }

}
