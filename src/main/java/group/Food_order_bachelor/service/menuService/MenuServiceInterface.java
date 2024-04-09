package group.Food_order_bachelor.service.menuService;

import group.Food_order_bachelor.dto.menu.CreateMenuDto;
import group.Food_order_bachelor.dto.menu.MenuToShowDto;
import group.Food_order_bachelor.model.Menu;
import group.Food_order_bachelor.model.Restaurant;

import java.util.List;

public interface MenuServiceInterface {
    Menu getMenuByIdString(String id);
    void createMenu(CreateMenuDto dto, Restaurant restaurant);
    void deleteMenu(String id);
    List<MenuToShowDto> getMenusForClientApp(String ids);
}
