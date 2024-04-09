package group.Food_order_bachelor.service.foodService;

import group.Food_order_bachelor.dto.food.*;
import group.Food_order_bachelor.dto.menu.MenuToShowDto;
import group.Food_order_bachelor.model.Food;
import group.Food_order_bachelor.model.Image;
import group.Food_order_bachelor.model.Menu;
import group.Food_order_bachelor.repository.FoodRepository;
import group.Food_order_bachelor.service.ingredientService.IngredientService;
import group.Food_order_bachelor.service.menuService.MenuService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService implements FoodServiceInterface {
    private final FoodRepository foodRepository;
    @Lazy
    private final MenuService menuService;
    private final IngredientService ingredientService;
    private final FoodAdapter foodAdapter = new FoodAdapter();
    @Override
    public void createFood(CreateFoodDto dto, Image image) {
        foodRepository.save(foodAdapter.createFoodDtoToFood(dto,menuService.getMenuByIdString(dto.getMenuId())
                ,ingredientService.getIngredientsByIds(dto.getIngredients()),image));
    }

    @Override
    public void deleteFood(String id) {
        foodRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void updatePrice(FoodPriceDto dto) {
        foodRepository.updatePrice(UUID.fromString(dto.getId()),dto.getPrice());
    }

    @Override
    public List<ViewFoodDto> getFoodsByMenuId(String menuId) {
        List<ViewFoodDto> dtos = new ArrayList<>();
        for (var food:
             foodRepository.selectFoodsByMenuId(UUID.fromString(menuId))) {
            dtos.add(foodAdapter.foodToViewFoodDto(food));
        }
        return dtos;
    }

    @Override
    public Food getFoodById(UUID id) { return foodRepository.getReferenceById(id); }

    @Override
    public void addFoodToMenu(AddOrChangeFoodFromMenuDto dto) {
        var menu = menuService.getMenuByIdString(dto.getMenuId());
        var food = foodRepository.getReferenceById(UUID.fromString(dto.getFoodId()));
        food.setMenu(menu);
    }

    @Override
    public void removeFoodFromMenu(String foodId) {
        var food = foodRepository.getReferenceById(UUID.fromString(foodId));
        food.setMenu(null);
    }

    @Override
    public void changeMenuForFood(AddOrChangeFoodFromMenuDto dto) {
        var menu = menuService.getMenuByIdString(dto.getMenuId());
        var food = foodRepository.getReferenceById(UUID.fromString(dto.getFoodId()));
        food.setMenu(menu);
    }

    @Override
    public void deselectMenuFromFood(String menuId) {
        foodRepository.updateMenuStatus(UUID.fromString(menuId));
    }

    @Override
    public List<Food> getFoodsByIds(List<String> ids) {
        List<Food> foods = new ArrayList<>();
        for(var id: ids){
            foods.add(foodRepository.getReferenceById(UUID.fromString(id)));
        }
        return foods;
    }

    @Override
    public void deselectIngredients(String ingredientId) {

    }

    @Override
    public List<FoodStatisticsDto> getFoodsForStatistics(Set<Menu> menus, String date) {
        List<FoodStatisticsDto> retList = new ArrayList<>();
        for(var menu:menus){
            retList.addAll(returnFoodStatisticsDtoByMenuId(foodRepository.selectFoodsByMenuId(menu.getId()),date));
        }
        return retList;
    }

    @Override
    public List<FoodForLoyaltyDto> getDrinksForStatistics(Set<Menu> menus) {
        List<FoodForLoyaltyDto> retList = new ArrayList<>();
        for(var menu:menus){
            retList.addAll(adaptDrinks(foodRepository.selectDrinks(menu.getId())));
        }
        return retList;
    }

    @Override
    public List<ViewFoodDto> getFoodsByMenuDto(List<MenuToShowDto> dtos) {
        List<ViewFoodDto> retList = new ArrayList<>();
        for(var menu: dtos){
            retList.addAll(getFoodsByMenuId(menu.getId()));
        }
        return retList;
    }

    @Override
    public List<ViewFoodDto> getFoodsByMenus(Set<Menu> menus) {
        List<ViewFoodDto> retList = new ArrayList<>();
        for(var menu: menus){
            retList.addAll(getFoodsByMenuId(menu.getId().toString()));
        }
        return retList;
    }

    private List<FoodForLoyaltyDto> adaptDrinks(List<Food> drinks){
        List<FoodForLoyaltyDto> retList = new ArrayList<>();
        for(var drink:drinks){
            retList.add(foodAdapter.foodToFoodForLoyaltyDto(drink));
        }
        return retList;
    }

    private List<FoodStatisticsDto> returnFoodStatisticsDtoByMenuId(List<Food> foods,String date){
        List<FoodStatisticsDto> retList = new ArrayList<>();
        for(var food:foods){
            retList.add(foodAdapter.foodToFoodStatisticsDto(food,date));
        }
        return retList;
    }


}
