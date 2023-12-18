package group.Food_order_bachelor.service.foodService;

import group.Food_order_bachelor.dto.food.CreateFoodDto;
import group.Food_order_bachelor.dto.food.FoodAdapter;
import group.Food_order_bachelor.dto.food.FoodPriceDto;
import group.Food_order_bachelor.dto.food.ViewFoodDto;
import group.Food_order_bachelor.dto.food.AddOrChangeFoodFromMenuDto;
import group.Food_order_bachelor.model.Food;
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
    public void createFood(CreateFoodDto dto) {
        foodRepository.save(foodAdapter.createFoodDtoToFood(dto,menuService.getMenuByIdString(dto.getMenuId())
                ,ingredientService.getIngredientsByIds(dto.getIngredients())));
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


}
