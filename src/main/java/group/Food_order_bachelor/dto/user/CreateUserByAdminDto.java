package group.Food_order_bachelor.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserByAdminDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String role;
    private int delivererSlots;
}
