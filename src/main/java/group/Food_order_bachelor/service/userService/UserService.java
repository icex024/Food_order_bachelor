package group.Food_order_bachelor.service.userService;

import group.Food_order_bachelor.dto.restaurant.AddManagerOrDriverToRestaurantDto;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;
import group.Food_order_bachelor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//                return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public void addManagerToRestaurant(AddManagerOrDriverToRestaurantDto dto, Restaurant restaurant) {
        var manager = userRepository.getReferenceById(UUID.fromString(dto.getUserId()));
        manager.setRestaurant(restaurant);
        userRepository.saveAndFlush(manager);
    }

    @Override
    public void addDriverToRestaurant(AddManagerOrDriverToRestaurantDto dto, Restaurant restaurant) {
        var driver = userRepository.getReferenceById(UUID.fromString(dto.getUserId()));
        driver.setRestaurant(restaurant);
        userRepository.saveAndFlush(driver);
    }

    @Override
    public User getUserById(UUID uuid) {
        return userRepository.getReferenceById(uuid);
    }

    @Override
    public void lowerSlotsForDeliverer(String delivererId) {
        var user = getUserById(UUID.fromString(delivererId));
        user.getDelivererSlots().setAvailableSlots(user.getDelivererSlots().getAvailableSlots()-1);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void increaseAvailableSlotsForDeliverer(String delivererId) {
        var user = getUserById(UUID.fromString(delivererId));
        user.getDelivererSlots().setAvailableSlots(user.getDelivererSlots().getAvailableSlots()+1);
        userRepository.saveAndFlush(user);
    }
}
