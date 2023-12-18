package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantByIdDto {
    private String id;
    private String name;
    private String description;
    private List<String> menuIds;
    private String streetName;
    private String streetNumber;
    private String city;
    private String country;
    private List<String> managerIds;
    private List<String> driverIds;
    private String workTimeStart;
    private String workTimeEnd;
    private List<String> loyaltyDefinitionIds;
}
