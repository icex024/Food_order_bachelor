package group.Food_order_bachelor.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DelivererTakeOrderDto {
    private String orderId;
    private String delivererId;
}
