package group.Food_order_bachelor.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {
    private String userId;
    private List<String> foodIds;
    private double price;
    private String note;
    private String paymentType;
}
