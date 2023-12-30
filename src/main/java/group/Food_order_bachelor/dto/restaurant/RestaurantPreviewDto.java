package group.Food_order_bachelor.dto.restaurant;

import lombok.*;
import org.springframework.core.io.Resource;

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
    private Resource image;
}
