package group.Food_order_bachelor.dto.food;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodStatisticsDto {
    private String id;
    private String name;
    private int numberOfOrders;
    private String date;
}
