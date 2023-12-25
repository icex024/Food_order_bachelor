package group.Food_order_bachelor.service.locationService;

import group.Food_order_bachelor.dto.coordinates.Coordinates;

public interface LocationServiceInterface  {
    void start(Coordinates start,Coordinates finish,String id);
    void deleteQueue();
}
