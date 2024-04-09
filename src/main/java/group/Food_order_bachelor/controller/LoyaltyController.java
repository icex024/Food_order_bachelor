package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.loyaltyDefinition.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.dto.loyaltyDefinition.ViewLoyaltyDefinitionDto;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.loyaltyDefinitionService.LoyaltyDefinitionService;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loyalty")
@RequiredArgsConstructor
public class LoyaltyController {
    private final LoyaltyDefinitionService loyaltyService;
    private final RestaurantService restaurantService;
    private final FoodService foodService;
    private final UserService userService;
    @PostMapping("/create-loyalty")
    @CrossOrigin("http://localhost:3000")
    public void createLoyalty(@RequestBody CreateLoyaltyDefinitionDto dto){
        try {
            loyaltyService.creteLoyalty(dto,userService.getUserById(UUID.fromString(dto.getManagerId())).getRestaurant()
                    ,foodService.getFoodById(UUID.fromString(dto.getFreeDrink())));
        }catch (Exception e){
            loyaltyService.creteLoyalty(dto,userService.getUserById(UUID.fromString(dto.getManagerId())).getRestaurant()
                    ,null);
        }
    }
    @GetMapping("/get-loyalties-for-manager")
    @CrossOrigin("http://localhost:3000")
    public List<ViewLoyaltyDefinitionDto> getLoyalties(@RequestParam String managerId){
        return loyaltyService.getLoyalties(userService.getUserById(UUID.fromString(managerId)).getRestaurant().getId());
    }
}
