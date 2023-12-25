package group.Food_order_bachelor.dto.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrdersForCustomerDto {
    private String customerId;
    private String restaurantId;
}
