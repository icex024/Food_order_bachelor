package group.Food_order_bachelor.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DelivererCancelOrderDto {
    private String orderId;
    private String delivererId;
}
