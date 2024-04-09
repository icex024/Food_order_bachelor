package group.Food_order_bachelor.service.menuService;

import group.Food_order_bachelor.dto.menu.CreateMenuDto;
import group.Food_order_bachelor.dto.menu.MenuAdapter;
import group.Food_order_bachelor.dto.menu.MenuToShowDto;
import group.Food_order_bachelor.model.Menu;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.repository.FoodRepository;
import group.Food_order_bachelor.repository.MenuRepository;
import group.Food_order_bachelor.service.restaurantService.RestaurantService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuServiceInterface {
    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;
    private final MenuAdapter menuAdapter = new MenuAdapter();
    private final EntityManager entityManager;
    @Override
    public Menu getMenuByIdString(String id) {
        return menuRepository.getReferenceById(UUID.fromString(id));
    }

    @Override
    public void createMenu(CreateMenuDto dto, Restaurant restaurant) {
        menuRepository.save(menuAdapter.createMenuDtoToMenu(dto,
                restaurant));
    }

    @Override
    @Transactional
    public void deleteMenu(String menu_id) {
        entityManager.createQuery("delete from Menu m where m.id = ?1")
                .setParameter(1,UUID.fromString(menu_id)).executeUpdate();
    }

    @Override
    public List<MenuToShowDto> getMenusForClientApp(String id) {
        List<Menu> menus = menuRepository.getMenusByRestaurant(UUID.fromString(id));
        List<MenuToShowDto> retList = new ArrayList<>();
        for(var menu: menus){
            retList.add(menuAdapter.menuToMenuToShow(menu));
        }
        return retList;
    }
}
