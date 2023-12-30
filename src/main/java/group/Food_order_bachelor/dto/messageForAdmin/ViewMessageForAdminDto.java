package group.Food_order_bachelor.dto.messageForAdmin;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewMessageForAdminDto {
    private String id;
    private String content;
    private String managerId;
}
