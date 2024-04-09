package group.Food_order_bachelor.service.userService;

import group.Food_order_bachelor.dto.restaurant.AddManagerOrDriverToRestaurantDto;
import group.Food_order_bachelor.dto.user.CreateUserByAdminDto;
import group.Food_order_bachelor.dto.user.ManagerOrDelivererPreviewDto;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {
    UserDetailsService userDetailsService();
    void addManagerToRestaurant(AddManagerOrDriverToRestaurantDto dto, Restaurant restaurant);
    void addDriverToRestaurant(AddManagerOrDriverToRestaurantDto dto, Restaurant restaurant);
    User getUserById(UUID uuid);
    void lowerSlotsForDeliverer(String delivererId);
    void increaseAvailableSlotsForDeliverer(String deliverId);
    String createDelivererOrManager(CreateUserByAdminDto dto);
    List<ManagerOrDelivererPreviewDto> getManagersAndDeliverersForPreview();
}
