package group.Food_order_bachelor.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewOrderDto {
    private String id;
    private String customerId;
    private String restaurantId;
    private List<String> foodIds;
    private double price;
    private String note;
    private String paymentType;
    private String timeOfMakingOrder;
    private int estimatedTime;
    private String status;
    private String delivererId;
}
