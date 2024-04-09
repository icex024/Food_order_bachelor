package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditRestaurantManagerDto {
    private String id;
    private String name;
    private String description;
    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
    private String workTimeStart;
    private String workTimeEnd;
    private double latitude;
    private double longitude;
}
