package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.food.CreateFoodDto;
import group.Food_order_bachelor.dto.food.FoodPriceDto;
import group.Food_order_bachelor.dto.food.ViewFoodDto;
import group.Food_order_bachelor.dto.food.AddOrChangeFoodFromMenuDto;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.imageService.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final ImageService imageService;

    @PostMapping("/create-food")
    @CrossOrigin("http://localhost:3000")
    public void createNewFood(@RequestBody CreateFoodDto dto){
        foodService.createFood(dto,imageService.getImageById(dto.getImageId()));
    }

    @DeleteMapping("/delete-food")
    @CrossOrigin("http://localhost:3000")
    public void deleteFood(@RequestParam String id){
        foodService.deleteFood(id);
    }

    @PatchMapping("/update-food-price")
    @CrossOrigin("http://localhost:3000")
    public void updatePrice(@RequestBody FoodPriceDto dto){
        foodService.updatePrice(dto);
    }

    @GetMapping("/get-foods-by-menu-id")
    @CrossOrigin("http://localhost:3000")
    public List<ViewFoodDto> getFoodsById(@RequestParam String id){
        return foodService.getFoodsByMenuId(id);
    }

    @PatchMapping("/add-food-to-menu")
    @CrossOrigin("http://localhost:3000")
    public void addFoodToMenu(@RequestBody AddOrChangeFoodFromMenuDto dto){
        foodService.addFoodToMenu(dto);
    }

    @PatchMapping("/remove-food-from-menu")
    @CrossOrigin("http://localhost:3000")
    public void removeFoodFromMenu(@RequestParam String foodId){
        foodService.removeFoodFromMenu(foodId);
    }

    @PatchMapping("/change-menu")
    @CrossOrigin("http://localhost:3000")
    public void changeMenu(@RequestBody AddOrChangeFoodFromMenuDto dto){
        foodService.changeMenuForFood(dto);
    }
}
