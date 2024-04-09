package group.Food_order_bachelor.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerOrDelivererPreviewDto {
    private String id;
    private String firstName;
    private String lastName;
    private String restaurantId;
    private String restaurantName;
    private String role;
}
