package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.menu.CreateMenuDto;
import group.Food_order_bachelor.service.foodService.FoodService;
import group.Food_order_bachelor.service.menuService.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final FoodService foodService;

    @PostMapping("/create-menu")
    @CrossOrigin("http://localhost:3000")
    public void createMenu(@RequestBody CreateMenuDto dto){
        menuService.createMenu(dto);
    }

    @DeleteMapping("/remove-menu")
    @CrossOrigin("http://localhost:3000")
    public void removeMenu(@RequestParam String id){
        foodService.deselectMenuFromFood(id);
        menuService.deleteMenu(id);
    }
}
