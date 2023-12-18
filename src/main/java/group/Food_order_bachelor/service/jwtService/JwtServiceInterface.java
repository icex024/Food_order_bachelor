package group.Food_order_bachelor.service.jwtService;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServiceInterface {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
