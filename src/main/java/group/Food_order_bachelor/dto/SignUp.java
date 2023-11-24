package group.Food_order_bachelor.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String city;
    private String streetName;
    private String streetNumber;
}
