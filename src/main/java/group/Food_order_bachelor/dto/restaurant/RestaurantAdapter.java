package group.Food_order_bachelor.dto.restaurant;

import group.Food_order_bachelor.model.LoyaltyDefinition;
import group.Food_order_bachelor.model.Menu;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;
import lombok.NoArgsConstructor;
import org.apache.catalina.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class RestaurantAdapter {
    public Restaurant createRestaurantDtoToRestaurant(CreateRestaurantDto dto){
        return Restaurant.builder().id(UUID.randomUUID()).name(dto.getName()).description(dto.getDescription())
                .streetName(dto.getStreetName()).streetNumber(dto.getStreetNumber()).city(dto.getCity())
                .country(dto.getCountry()).workTimeStart(dto.getWorkTimeStart()).workTimeEnd(dto.getWorkTimeEnd())
                .build();
    }

    public RestaurantPreviewDto restaurantToRestaurantPreviewDto(Restaurant restaurant){
        return RestaurantPreviewDto.builder().id(restaurant.getId().toString()).description(restaurant.getDescription())
                .name(restaurant.getName()).workTimeEnd(restaurant.getWorkTimeEnd())
                .workTimeStart(restaurant.getWorkTimeStart()).build();
    }

    public GetRestaurantByIdDto restaurantToGetRestaurantByIdDto(Restaurant restaurant){
        List<String> managerIds = managers(restaurant.getManagers());
        List<String> driverIds = drivers(restaurant.getDrivers());
        List<String> menuIds = menus(restaurant.getMenus());
        List<String> loyaltiesIds = loyalties(restaurant.getLoyaltyDefinitions());
        return GetRestaurantByIdDto.builder().id(restaurant.getId().toString()).name(restaurant.getName())
                .description(restaurant.getDescription()).menuIds(menuIds).streetName(restaurant.getStreetName())
                .streetNumber(restaurant.getStreetNumber()).city(restaurant.getCity()).country(restaurant.getCountry())
                .managerIds(managerIds).driverIds(driverIds).workTimeEnd(restaurant.getWorkTimeEnd())
                .workTimeStart(restaurant.getWorkTimeStart()).loyaltyDefinitionIds(loyaltiesIds)
                .build();
    }

    private List<String> managers(Set<User> list){
        List<String> ids = new ArrayList<>();
        for (var object:
                list) {
            ids.add(object.getId().toString());
        }
        return ids;
    }
    private  List<String> drivers(Set<User> list){
        List<String> ids = new ArrayList<>();
        for (var object:
                list) {
            ids.add(object.getId().toString());
        }
        return ids;
    }
    private List<String> menus(Set<Menu> list){
        List<String> ids = new ArrayList<>();
        for (var object:
                list) {
            ids.add(object.getId().toString());
        }
        return ids;
    }
    private List<String> loyalties(Set<LoyaltyDefinition> list){
        List<String> ids = new ArrayList<>();
        for (var object:
                list) {
            ids.add(object.getId().toString());
        }
        return ids;
    }
}
