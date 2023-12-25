package group.Food_order_bachelor.service.openRouteService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.dto.coordinates.CoordinatesAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenRouteService implements OpenRouteServiceInterface {
    @Value("${openroute-service.api.key}")
    private String API_KEY;
    @Value("${openroute-service.api.endpoint}")
    private String DIRECTIONS_API_URL;
    @Value("${openroute-service.api.encode.endpoint}")
    private String ENCODE_API_URL;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();
    private final CoordinatesAdapter coordinatesAdapter = new CoordinatesAdapter();

    public List<Coordinates> getRoute(Coordinates start, Coordinates finish){
        String url = DIRECTIONS_API_URL + "?api_key=" + API_KEY + "&start=" + start.getLng() + "," + start.getLat() + "&end=" +
                finish.getLng() + "," + finish.getLat();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,null,String.class);

        JsonObject jsonResponse = gson.fromJson(response.getBody(),JsonObject.class);
        JsonArray coordinates = jsonResponse.getAsJsonArray("features").get(0).getAsJsonObject()
                .getAsJsonObject("geometry").getAsJsonArray("coordinates");

        List<Coordinates> routeCoordinates = new ArrayList<>();

        for(var coordinate: coordinates){
          JsonArray coord = coordinate.getAsJsonArray();
          routeCoordinates.add(new Coordinates(coord.get(0).getAsDouble(), coord.get(1).getAsDouble()));
        }

        return routeCoordinates;
    }

    @Override
    public void getCoordinates(String address) {
        String url = ENCODE_API_URL + "?api_key=" + API_KEY + "&text=" + address;
        var response  = restTemplate.exchange(url,HttpMethod.GET,null,String.class);
        JsonObject jsonResponse = gson.fromJson(response.getBody(),JsonObject.class);
        var coordinate = jsonResponse.getAsJsonArray("features").get(0).getAsJsonObject()
                .getAsJsonObject("geometry").getAsJsonArray("coordinates");

        System.out.println(coordinate);
    }


}
