package group.Food_order_bachelor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    private String name;
    private String username;
    private String email;
    private String password;
}
