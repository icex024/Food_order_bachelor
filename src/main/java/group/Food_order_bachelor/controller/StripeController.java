package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.stripe.StripeChargeDto;
import group.Food_order_bachelor.dto.stripe.StripeTokenDto;
import group.Food_order_bachelor.service.stripeService.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stripe")
@RequiredArgsConstructor
public class StripeController {
    private final StripeService stripeService;

    @PostMapping("/card/token")
    @CrossOrigin("http://localhost:3000")
    public String createCardToken() {
        return stripeService.createCardToken();
    }

    @PostMapping("/charge")
    @ResponseBody
    @CrossOrigin("http://localhost:3000")
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {
        return stripeService.charge(model);
    }

}
