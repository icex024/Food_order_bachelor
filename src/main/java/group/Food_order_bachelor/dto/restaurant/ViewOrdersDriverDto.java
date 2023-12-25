package group.Food_order_bachelor.dto.restaurant;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewOrdersDriverDto {
    private String id;
    private List<String> foodIds;
    private String city;
    private String streetNumber;
    private String streetName;
    private double price;
    private String note;
    private String paymentType;
}
