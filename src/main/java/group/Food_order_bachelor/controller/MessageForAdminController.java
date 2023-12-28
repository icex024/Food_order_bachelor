package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.dto.messageForAdmin.CreateMessageForAdminDto;
import group.Food_order_bachelor.dto.messageForAdmin.ViewMessageForAdminDto;
import group.Food_order_bachelor.service.messageForAdminService.MessageForAdminService;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/message-for-admin")
@RequiredArgsConstructor
public class MessageForAdminController {
    private final UserService userService;
    private final MessageForAdminService messageForAdminService;

    @PostMapping("/create-message")
    @CrossOrigin("http://localhost:3000")
    public void createMessage(@RequestBody CreateMessageForAdminDto dto){
        messageForAdminService.createMessageForAdmin(dto,userService.getUserById(UUID.fromString(dto.getManagerId())));
    }

    @GetMapping("/get-messages")
    @CrossOrigin("http://localhost:3000")
    public List<ViewMessageForAdminDto> getMessages(){
        return messageForAdminService.getMessages();
    }

    @PatchMapping("/review-message")
    @CrossOrigin("http://localhost:3000")
    public void reviewMessage(@RequestParam String messageId){
        messageForAdminService.changeStatusToReviewed(messageId);
    }
}
