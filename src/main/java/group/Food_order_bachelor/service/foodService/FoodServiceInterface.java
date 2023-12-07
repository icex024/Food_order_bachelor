package group.Food_order_bachelor.service.foodService;

import group.Food_order_bachelor.dto.food.CreateFoodDto;
import group.Food_order_bachelor.dto.food.FoodPriceDto;
import group.Food_order_bachelor.dto.food.ViewFoodDto;
import group.Food_order_bachelor.dto.food.AddOrChangeFoodFromMenuDto;
import group.Food_order_bachelor.model.Food;

import java.util.List;
import java.util.UUID;

public interface FoodServiceInterface {
    void createFood(CreateFoodDto dto);
    void deleteFood(String id);
    void updatePrice(FoodPriceDto dto);
    List<ViewFoodDto> getFoodsByMenuId(String menuId);

    Food getFoodById(UUID id);
    void addFoodToMenu(AddOrChangeFoodFromMenuDto dto);
    void removeFoodFromMenu(String foodId);
    void changeMenuForFood(AddOrChangeFoodFromMenuDto dto);
    void deselectMenuFromFood(String menuId);
}
