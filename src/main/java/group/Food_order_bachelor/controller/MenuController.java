package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.menu.CreateMenuDto;
import group.Food_order_bachelor.dto.menu.MenuToShowDto;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.menuService.MenuService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final FoodService foodService;
    private final UserService userService;

    @PostMapping("/create-menu")
    @CrossOrigin("http://localhost:3000")
    public void createMenu(@RequestBody CreateMenuDto dto){
        menuService.createMenu(dto,userService.getUserById(UUID.fromString(dto.getManagerId())).getRestaurant());
    }

    @DeleteMapping("/remove-menu")
    @CrossOrigin("http://localhost:3000")
    public void removeMenu(@RequestParam String id){
        foodService.deselectMenuFromFood(id);
        menuService.deleteMenu(id);
    }

    @GetMapping("/get-menus")
    @CrossOrigin("http://localhost:3000")
    public List<MenuToShowDto> getMenus(@RequestParam String id){

        return menuService.getMenusForClientApp(id);
    }

    @GetMapping("/get-menus-for-manager")
    @CrossOrigin("http://localhost:3000")
    public List<MenuToShowDto> getMenusForManager(@RequestParam String managerId){
        return menuService.getMenusForClientApp(
                userService.getUserById(UUID.fromString(managerId)).getRestaurant().getId().toString());
    }
}
