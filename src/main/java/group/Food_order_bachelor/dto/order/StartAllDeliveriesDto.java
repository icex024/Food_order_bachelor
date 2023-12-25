package group.Food_order_bachelor.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StartAllDeliveriesDto {
    private double longitudeStart;
    private double latitudeStart;
    private double longitudeFinish;
    private double latitudeFinish;
    private String delivererId;
    private List<String> orderIds;
}
