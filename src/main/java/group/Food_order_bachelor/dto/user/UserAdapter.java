package group.Food_order_bachelor.dto.user;

import group.Food_order_bachelor.enums.User_role;
import group.Food_order_bachelor.model.DelivererSlots;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;

import java.util.UUID;

public class UserAdapter {
    public User createUserByAdminDtoToUser(CreateUserByAdminDto dto){
        UUID id = UUID.randomUUID();
        return User.builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .role(getRole(dto.getRole()))
                .build();
    }

    public ManagerOrDelivererPreviewDto userToManagerOrDelivererPreviewDto(User user){
        var restaurant = user.getRestaurant();
        return ManagerOrDelivererPreviewDto.builder()
                .id(user.getId().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .restaurantId(getRestaurantId(user.getRestaurant()))
                .restaurantName(getRestaurantName(user.getRestaurant()))
                .role(user.getRole().toString())
                .build();
    }

    private User_role getRole(String role){
        if(role.equals("MANAGER")){
            return User_role.MANAGER;
        }else{
            return User_role.DELIVERER;
        }
    }

    private String getRestaurantId(Restaurant restaurant){
       if(restaurant != null){
           return restaurant.getId().toString();
       }else{
           return "";
       }
    }

    private String getRestaurantName(Restaurant restaurant){
        if(restaurant != null){
            return restaurant.getName();
        }else{
            return "";
        }
    }
}
