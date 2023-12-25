package group.Food_order_bachelor.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StartDeliveryDto {
    private double longitudeStart;
    private double latitudeStart;
    private double longitudeFinish;
    private double latitudeFinish;
    private String delivererId;
}
