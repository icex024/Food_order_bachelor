package group.Food_order_bachelor.dto.messageForAdmin;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageForAdminDto {
    private String content;
    private String managerId;
}
