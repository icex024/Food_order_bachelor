package group.Food_order_bachelor.service.userService;

import group.Food_order_bachelor.dto.restaurant.AddManagerOrDriverToRestaurantDto;
import group.Food_order_bachelor.model.Restaurant;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface {
    UserDetailsService userDetailsService();
    void addManagerToRestaurant(AddManagerOrDriverToRestaurantDto dto, Restaurant restaurant);

    void addDriverToRestaurant(AddManagerOrDriverToRestaurantDto dto, Restaurant restaurant);
}
