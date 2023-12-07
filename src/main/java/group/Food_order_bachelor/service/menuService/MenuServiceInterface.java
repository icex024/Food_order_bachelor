package group.Food_order_bachelor.service.menuService;

import group.Food_order_bachelor.dto.menu.CreateMenuDto;
import group.Food_order_bachelor.model.Menu;

public interface MenuServiceInterface {
    Menu getMenuByIdString(String id);
    void createMenu(CreateMenuDto dto);
    void deleteMenu(String id);
}
