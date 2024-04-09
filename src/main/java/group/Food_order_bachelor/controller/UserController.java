package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.restaurant.AddManagerOrDriverToRestaurantDto;
import group.Food_order_bachelor.dto.user.CreateUserByAdminDto;
import group.Food_order_bachelor.dto.user.ManagerOrDelivererPreviewDto;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RestaurantService restaurantService;

    @PatchMapping("/add-manager-or-deliverer-to-restaurant")
    @CrossOrigin("http://localhost:3000")
    public void addManager(@RequestBody AddManagerOrDriverToRestaurantDto dto){
        userService.addManagerToRestaurant(dto,restaurantService.getById(UUID.fromString(dto.getRestaurantId())));
    }

    @PostMapping("/create-manager-or-deliverer")
    @CrossOrigin("http://localhost:3000")
    public String createManagerOrDeliverer(@RequestBody CreateUserByAdminDto dto){//ovo doradi da vrati redovan error code
        return userService.createDelivererOrManager(dto);
    }

    @GetMapping("/get-available-slots")
    @CrossOrigin("http://localhost:3000")
    public int getSlots(@RequestParam String delivererId){
        return userService.getUserById(UUID.fromString(delivererId)).getDelivererSlots().getAvailableSlots();
    }

    @GetMapping("/get-deliverers-and-managers")
    @CrossOrigin("http://localhost:3000")
    public List<ManagerOrDelivererPreviewDto> getManAndDel(){
        return userService.getManagersAndDeliverersForPreview();
    }
}
