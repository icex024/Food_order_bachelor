package group.Food_order_bachelor.service.stripeService;

import group.Food_order_bachelor.dto.stripe.StripeChargeDto;
import group.Food_order_bachelor.dto.stripe.StripeTokenDto;

public interface StripeServiceInterface {
    String createCardToken();
    StripeChargeDto charge(StripeChargeDto chargeRequest);
}
