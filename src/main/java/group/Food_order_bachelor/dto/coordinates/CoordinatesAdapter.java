package group.Food_order_bachelor.dto.coordinates;

public class CoordinatesAdapter {
    public Coordinates coordinatesParamsToCoordinates(double lat,double lng){
        return Coordinates.builder().lat(lat).lng(lng).build();
    }
}
