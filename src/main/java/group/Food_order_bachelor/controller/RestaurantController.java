package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.restaurant.CreateRestaurantDto;
import group.Food_order_bachelor.dto.restaurant.GetRestaurantByIdDto;
import group.Food_order_bachelor.dto.restaurant.RestaurantPreviewDto;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/create-restaurant")
    @CrossOrigin("http://localhost:3000")
    public void createRestaurant(@RequestBody CreateRestaurantDto dto){
        restaurantService.createRestaurant(dto);
    }

    @PutMapping("/edit-restaurant-manager")
    @CrossOrigin("http://localhost:3000")
    public void editRestaurantManager(){

    }

    @PatchMapping("/edit-restaurant-admin")
    @CrossOrigin("http://localhost:3000")
    public void editRestaurantAdmin(){

    }

    @GetMapping("/get-restaurants")
    @CrossOrigin("http://localhost:3000")
    public List<RestaurantPreviewDto> getRestaurants(){
        return restaurantService.getRestaurantsForPreview();
    }

    @GetMapping("/get-restaurant")
    @CrossOrigin("http://localhost:3000")
    public GetRestaurantByIdDto getRestaurant(@RequestParam String id){
        return restaurantService.getRestaurantByIdForClient(UUID.fromString(id));
    }
}
