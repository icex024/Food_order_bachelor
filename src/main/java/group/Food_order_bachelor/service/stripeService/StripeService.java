package group.Food_order_bachelor.service.stripeService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import group.Food_order_bachelor.dto.stripe.StripeChargeDto;
import group.Food_order_bachelor.dto.stripe.StripeTokenDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeService implements StripeServiceInterface{
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    public String createCardToken() {

        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", "4242 4242 4242 4242");
            card.put("exp_month", 12);
            card.put("exp_year", 34);
            card.put("cvc", 567);
//            card.put("")
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
//            if (token != null && token.getId() != null) {
//                model.setSuccess(true);
//                model.setToken(token.getId());
//            }
            return token.getId();
        } catch (StripeException e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

    public StripeChargeDto charge(StripeChargeDto chargeRequest) {


        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);

            }
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

}
