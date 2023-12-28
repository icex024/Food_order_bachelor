package group.Food_order_bachelor.dto.messageForAdmin;

import group.Food_order_bachelor.enums.Message_for_admin_status;
import group.Food_order_bachelor.model.MessageForAdmin;
import group.Food_order_bachelor.model.User;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class MessageForAdminAdapter {
    public MessageForAdmin createMessageForAdminDtoToMessageForAdmin(CreateMessageForAdminDto dto, User manager){
        return MessageForAdmin.builder()
                .id(UUID.randomUUID())
                .content(dto.getContent())
                .manager(manager)
                .messageForAdminStatus(Message_for_admin_status.NOT_REVIEWED)
                .build();
    }

    public ViewMessageForAdminDto messageForAdminToViewMessageForAdminDto(MessageForAdmin messageForAdmin){
        return ViewMessageForAdminDto.builder()
                .id(messageForAdmin.getId().toString())
                .content(messageForAdmin.getContent())
                .managerId(messageForAdmin.getManager().getId().toString())
                .build();
    }
}
