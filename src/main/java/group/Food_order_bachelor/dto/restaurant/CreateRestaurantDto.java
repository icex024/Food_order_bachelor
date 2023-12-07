package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantDto {
    private String name;
    private String description;
    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
    private String workTimeStart;
    private String workTimeEnd;
}
