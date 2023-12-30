package group.Food_order_bachelor.service.restaurantService;

import group.Food_order_bachelor.dto.restaurant.*;
import group.Food_order_bachelor.enums.Order_status;
import group.Food_order_bachelor.model.Image;
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
    public void createRestaurant(CreateRestaurantDto dto, Image image) {
        restaurantRepository.save(restaurantAdapter.createRestaurantDtoToRestaurant(dto,image));
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

    @Override
    public List<ViewOrdersDriverDto> viewReadyOrdersForDeliverer(String restaurantId) {
        List<ViewOrdersDriverDto> dtos = new ArrayList<>();
        for(var order: restaurantRepository.getReferenceById(UUID.fromString(restaurantId)).getOrders()){
            if(order.getStatus().equals(Order_status.READY.name())) {
                dtos.add(restaurantAdapter.OrderToViewOrdersDriverDto(order));
            }
        }
        return dtos;
    }
}
