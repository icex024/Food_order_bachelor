package group.Food_order_bachelor.service.authenticationService;

import group.Food_order_bachelor.dto.JwtAuthenticationResponse;
import group.Food_order_bachelor.dto.SignIn;
import group.Food_order_bachelor.dto.SignUp;

public interface AuthenticationServiceInterface {
    JwtAuthenticationResponse signup(SignUp request);
    JwtAuthenticationResponse signin(SignIn request);
}
