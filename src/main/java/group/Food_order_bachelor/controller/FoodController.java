package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.food.*;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.imageService.ImageService;
import group.Food_order_bachelor.service.menuService.MenuService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final ImageService imageService;
    private final UserService userService;
    private final MenuService menuService;

    @PostMapping("/create-food")
    @CrossOrigin("http://localhost:3000")
    public void createNewFood(@RequestPart CreateFoodDto dto,@RequestPart MultipartFile image){
        var imageId = imageService.buildImager(image);
        foodService.createFood(dto,imageService.getImageById(imageId));
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

    @GetMapping("/get-foods-by-restaurant-id")
    @CrossOrigin("http://localhost:3000")
    public List<ViewFoodDto> getFoodsByRestaurantId(@RequestParam String id) {
        return foodService.getFoodsByMenuDto(menuService.getMenusForClientApp(id));
    }

    @GetMapping("/get-foods-by-user-id")
    @CrossOrigin("http://localhost:3000")
    public List<ViewFoodDto> getFoodsByUserId(@RequestParam String id) {
        return foodService.getFoodsByMenus(userService.getUserById(UUID.fromString(id)).getRestaurant().getMenus());
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

    @GetMapping("/fetch-drinks-for-loyalty")
    @CrossOrigin("http://localhost:3000")
    public List<FoodForLoyaltyDto> getFoodForLoyalty(@RequestParam String managerId){
        return foodService.getDrinksForStatistics(
                userService.getUserById(UUID.fromString(managerId)).getRestaurant().getMenus());
    }
}
