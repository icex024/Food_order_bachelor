package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.service.locationService.LocationService;
import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.service.openRouteService.OpenRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/location-test")
@RequiredArgsConstructor
public class LocationTestController {
    private final LocationService locationService;
    private final OpenRouteService openRouteService;

    @PostMapping("/routes")
    @CrossOrigin("http://localhost:3000")
    public void test(@RequestParam String id){
        locationService.start(
                Coordinates.builder().lat(45.23992).lng(19.82307).build(),
                Coordinates.builder().lat(45.24976).lng(19.83755).build(),
                id);
    }

    @DeleteMapping("/delete-queue")
    @CrossOrigin("http://localhost:3000")
    public void deleteQueue(){
        locationService.deleteQueue();
    }

    @GetMapping("/geocode-test")
    @CrossOrigin("http://localhost:3000")
    public void geocodeTest(@RequestParam String addressTest){
        openRouteService.getCoordinates(addressTest);
    }

}
