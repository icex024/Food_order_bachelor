package group.Food_order_bachelor.dto.menu;

import group.Food_order_bachelor.dto.food.ViewFoodDto;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Image;
import group.Food_order_bachelor.model.Menu;
import group.Food_order_bachelor.model.Restaurant;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class MenuAdapter {
    public Menu createMenuDtoToMenu(CreateMenuDto dto, Restaurant restaurant){
        return Menu.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .restaurant(restaurant)
                .build();
    }

    public MenuToShowDto menuToMenuToShow(Menu menu){
        return MenuToShowDto.builder()
                .id(menu.getId().toString())
                .name(menu.getName())
                .build();
    }

    public MenuAndFoodDto MenuToMenuAndFood(Menu menu){
        return MenuAndFoodDto.builder()
                .id(menu.getId().toString())
                .name(menu.getName())
                .foods(getViewFoodDtos(menu.getFoods())).build();
    }

    private List<ViewFoodDto> getViewFoodDtos(Set<Food> foods){
        List<ViewFoodDto> viewFoodDtos = new ArrayList<>();
        for(Food food : foods){
            viewFoodDtos.add(foodToViewFoodDto(food));
        }
        return  viewFoodDtos;
    }

    private ViewFoodDto foodToViewFoodDto(Food food){
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

    private byte[] checkImage(Image image){
        if(image == null){
            return null;
        }else{
//            ByteArrayInputStream stream = new ByteArrayInputStream(image.getData());
            return image.getData();
        }
    }
}
