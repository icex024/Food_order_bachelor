package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.loyalty.CreateLoyaltyDefinitionDto;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.loyaltyService.LoyaltyService;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loyalty")
@RequiredArgsConstructor
public class LoyaltyController {
    private final LoyaltyService loyaltyService;
    private final RestaurantService restaurantService;
    private final FoodService foodService;
    @PostMapping("/create-loyalty")
    @CrossOrigin("http://localhost:3000")
    public void createLoyalty(@RequestBody CreateLoyaltyDefinitionDto dto){
        try {
            loyaltyService.creteLoyalty(dto,restaurantService.getById(UUID.fromString(dto.getRestaurantId()))
                    ,foodService.getFoodById(UUID.fromString(dto.getFreeDrink())));
        }catch (Exception e){
            loyaltyService.creteLoyalty(dto,restaurantService.getById(UUID.fromString(dto.getRestaurantId()))
                    ,null);
        }
    }
}
