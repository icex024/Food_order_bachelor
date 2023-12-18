package group.Food_order_bachelor.service.restaurantService;

import group.Food_order_bachelor.dto.restaurant.CreateRestaurantDto;
import group.Food_order_bachelor.dto.restaurant.GetRestaurantByIdDto;
import group.Food_order_bachelor.dto.restaurant.RestaurantAdapter;
import group.Food_order_bachelor.dto.restaurant.RestaurantPreviewDto;
import group.Food_order_bachelor.model.Restaurant;
import group.Food_order_bachelor.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService implements RestaurantServiceInterface {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantAdapter restaurantAdapter = new RestaurantAdapter();
    @Override
    public Restaurant getById(UUID uuid) {
        return restaurantRepository.getReferenceById(uuid);
    }

    @Override
    public void createRestaurant(CreateRestaurantDto dto) {
        restaurantRepository.save(restaurantAdapter.createRestaurantDtoToRestaurant(dto));
    }

    @Override
    public List<RestaurantPreviewDto>  getRestaurantsForPreview() {
        List<RestaurantPreviewDto> dtoList = new ArrayList<>();
        for (var restaurant:
                restaurantRepository.findAll()) {
            dtoList.add(restaurantAdapter.restaurantToRestaurantPreviewDto(restaurant));
        }
        return dtoList;
    }

    @Override
    public GetRestaurantByIdDto getRestaurantByIdForClient(UUID uuid) {
        return restaurantAdapter.restaurantToGetRestaurantByIdDto(restaurantRepository.getReferenceById(uuid));
    }
}
