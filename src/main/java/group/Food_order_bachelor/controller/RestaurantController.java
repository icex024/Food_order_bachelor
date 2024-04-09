package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.restaurant.*;
import group.Food_order_bachelor.service.imageService.ImageService;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ImageService imageService;

    @PostMapping("/create-restaurant")
    @CrossOrigin("http://localhost:3000")
    public void createRestaurant(@RequestPart CreateRestaurantDto dto,@RequestPart MultipartFile image){
        var imageId = imageService.buildImager(image);
        restaurantService.createRestaurant(dto,imageService.getImageById(imageId));
    }

    @PutMapping("/edit-restaurant")
    @CrossOrigin("http://localhost:3000")
    public void editRestaurantManager(@RequestBody EditRestaurantManagerDto dto){
        restaurantService.editRestaurant(dto);
    }

    @PatchMapping("/change-restaurant-status")
    @CrossOrigin("http://localhost:3000")
    public void editRestaurantAdmin(@RequestBody EditRestaurantStatusDto dto){
        restaurantService.changeRestaurantStatus(dto);
    }

    @GetMapping("/get-restaurants")
    @CrossOrigin("http://localhost:3000")
    public List<RestaurantPreviewDto> getRestaurants(){
        var restaurants = restaurantService.getRestaurantsForPreview();

        return restaurants;
    }

    @GetMapping("/get-restaurant")
    @CrossOrigin("http://localhost:3000")
    public GetRestaurantByIdDto getRestaurant(@RequestParam String id){
        return restaurantService.getRestaurantByIdForClient(UUID.fromString(id));
    }

    @GetMapping("/get-ready-orders-for-deliverer")
    @CrossOrigin("http://localhost:3000")
    public List<ViewOrdersDriverDto> getOrdersForDeliverer(@RequestParam String restaurantId){
        return restaurantService.viewReadyOrdersForDeliverer(restaurantId);
    }


}
