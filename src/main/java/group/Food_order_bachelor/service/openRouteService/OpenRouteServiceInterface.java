package group.Food_order_bachelor.service.openRouteService;

import group.Food_order_bachelor.dto.coordinates.Coordinates;

import java.util.List;

public interface OpenRouteServiceInterface {
    List<Coordinates> getRoute(Coordinates start, Coordinates finish);

    void getCoordinates(String address);
}
