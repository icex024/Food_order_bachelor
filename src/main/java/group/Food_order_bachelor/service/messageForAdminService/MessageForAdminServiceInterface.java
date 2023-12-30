package group.Food_order_bachelor.service.messageForAdminService;

import group.Food_order_bachelor.dto.messageForAdmin.CreateMessageForAdminDto;
import group.Food_order_bachelor.dto.messageForAdmin.ViewMessageForAdminDto;
import group.Food_order_bachelor.model.User;

import java.util.List;

public interface MessageForAdminServiceInterface {
    void createMessageForAdmin(CreateMessageForAdminDto dto, User manager);
    List<ViewMessageForAdminDto> getMessages();
    void changeStatusToReviewed(String messageId);
}
