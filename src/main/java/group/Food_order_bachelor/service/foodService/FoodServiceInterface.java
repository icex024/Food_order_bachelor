package group.Food_order_bachelor.service.foodService;

import group.Food_order_bachelor.dto.food.*;
import group.Food_order_bachelor.dto.menu.MenuToShowDto;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Image;
import group.Food_order_bachelor.model.Menu;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FoodServiceInterface {
    void createFood(CreateFoodDto dto, Image image);
    void deleteFood(String id);
    void updatePrice(FoodPriceDto dto);
    List<ViewFoodDto> getFoodsByMenuId(String menuId);
    Food getFoodById(UUID id);
    void addFoodToMenu(AddOrChangeFoodFromMenuDto dto);
    void removeFoodFromMenu(String foodId);
    void changeMenuForFood(AddOrChangeFoodFromMenuDto dto);
    void deselectMenuFromFood(String menuId);
    List<Food> getFoodsByIds(List<String> ids);
    void deselectIngredients(String ingredientId);
    List<FoodStatisticsDto> getFoodsForStatistics(Set<Menu> menus,String date);
    List<FoodForLoyaltyDto> getDrinksForStatistics(Set<Menu> menus);
    List<ViewFoodDto>  getFoodsByMenuDto(List<MenuToShowDto> dtos);
    List<ViewFoodDto>  getFoodsByMenus(Set<Menu> menus);
}
