package group.Food_order_bachelor.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import group.Food_order_bachelor.dto.JwtAuthenticationResponse;
import group.Food_order_bachelor.dto.SignIn;
import group.Food_order_bachelor.dto.SignUp;
import group.Food_order_bachelor.enums.User_role;
import group.Food_order_bachelor.service.authenticationService.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUp request){
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignIn request){
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
