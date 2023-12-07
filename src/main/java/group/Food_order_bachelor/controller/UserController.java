package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.restaurant.AddManagerOrDriverToRestaurantDto;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RestaurantService restaurantService;

    @PatchMapping("/api/v1/add-manager")
    @CrossOrigin("http://localhost:3000")
    public void addManager(@RequestBody AddManagerOrDriverToRestaurantDto dto){
        userService.addManagerToRestaurant(dto,restaurantService.getById(UUID.fromString(dto.getRestaurantId())));
    }


}
