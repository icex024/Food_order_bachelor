package group.Food_order_bachelor.service.userService;

import group.Food_order_bachelor.dto.restaurant.AddManagerOrDriverToRestaurantDto;
import group.Food_order_bachelor.dto.user.CreateUserByAdminDto;
import group.Food_order_bachelor.dto.user.ManagerOrDelivererPreviewDto;
import group.Food_order_bachelor.dto.user.UserAdapter;
import group.Food_order_bachelor.model.DelivererSlots;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.model.User;
import group.Food_order_bachelor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final UserAdapter userAdapter = new UserAdapter();

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

    @Override
    public String createDelivererOrManager(CreateUserByAdminDto dto) {
        if(userRepository.findUserByUsername(dto.getUsername()).isPresent()){
            return "User with this username already exists";
        }
        var user = userAdapter.createUserByAdminDtoToUser(dto);
        if(user.getRole().toString().equals("DELIVERER")){
            user.setDelivererSlots(
                    DelivererSlots.builder()
                    .id(UUID.randomUUID())
                    .user(user)
                    .maxSlots(dto.getDelivererSlots())
                    .availableSlots(dto.getDelivererSlots())
                    .build());
        }
        userRepository.saveAndFlush(user);
        return "Ok";
    }

    @Override
    public List<ManagerOrDelivererPreviewDto> getManagersAndDeliverersForPreview() {
        List<ManagerOrDelivererPreviewDto> retList = new ArrayList<>();
        for(var user: userRepository.findManagersAndDeliverers()){
            retList.add(userAdapter.userToManagerOrDelivererPreviewDto(user));
        }
        return retList;
    }
}
