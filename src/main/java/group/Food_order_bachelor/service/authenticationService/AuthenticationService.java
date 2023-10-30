package group.Food_order_bachelor.service.authenticationService;

import group.Food_order_bachelor.dto.JwtAuthenticationResponse;
import group.Food_order_bachelor.dto.SignIn;
import group.Food_order_bachelor.dto.SignUp;
import group.Food_order_bachelor.enums.User_role;
import group.Food_order_bachelor.model.User;
import group.Food_order_bachelor.repository.UserRepository;
import group.Food_order_bachelor.service.jwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceInterface{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUp request) {
        var user = User.builder().name(request.getName()).username(request.getUsername()).
                email(request.getEmail()).password(request.getPassword()).role(User_role.CUSTOMER).
                build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignIn request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(()->new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
