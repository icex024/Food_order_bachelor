package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPreviewDto {
    private String id;
    private String name;
    private String description;
    private String workTimeStart;
    private String workTimeEnd;
}
