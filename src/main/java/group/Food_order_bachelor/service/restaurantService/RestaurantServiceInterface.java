package group.Food_order_bachelor.service.restaurantService;

import group.Food_order_bachelor.dto.restaurant.CreateRestaurantDto;
import group.Food_order_bachelor.dto.restaurant.GetRestaurantByIdDto;
import group.Food_order_bachelor.dto.restaurant.RestaurantPreviewDto;
import group.Food_order_bachelor.dto.restaurant.ViewOrdersDriverDto;
import group.Food_order_bachelor.model.Image;
import group.Food_order_bachelor.model.Restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantServiceInterface {
    Restaurant getById(UUID uuid);
    void createRestaurant(CreateRestaurantDto dto, Image image);
    List<RestaurantPreviewDto>  getRestaurantsForPreview();
    GetRestaurantByIdDto getRestaurantByIdForClient(UUID uuid);

    List<ViewOrdersDriverDto> viewReadyOrdersForDeliverer(String restaurantId);
}
