package group.Food_order_bachelor.dto.menu;

import group.Food_order_bachelor.model.Menu;
import group.Food_order_bachelor.model.Restaurant;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.UUID;

@NoArgsConstructor
public class MenuAdapter {
    public Menu createMenuDtoToMenu(CreateMenuDto dto, Restaurant restaurant){
        return Menu.builder().id(UUID.randomUUID()).name(dto.getName()).restaurant(restaurant).build();
    }
}
