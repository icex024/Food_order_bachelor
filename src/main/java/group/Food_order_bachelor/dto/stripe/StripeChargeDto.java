package group.Food_order_bachelor.dto.stripe;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StripeChargeDto {
    private String  stripeToken;
    private String  username;
    private Double  amount;
    private Boolean success;
    private String  message;
    private String chargeId;
    private Map<String,Object> additionalInfo = new HashMap<>();

}
