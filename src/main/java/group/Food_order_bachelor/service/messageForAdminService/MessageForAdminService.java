package group.Food_order_bachelor.service.messageForAdminService;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import group.Food_order_bachelor.dto.messageForAdmin.CreateMessageForAdminDto;
import group.Food_order_bachelor.dto.messageForAdmin.MessageForAdminAdapter;
import group.Food_order_bachelor.dto.messageForAdmin.ViewMessageForAdminDto;
import group.Food_order_bachelor.enums.Message_for_admin_status;
import group.Food_order_bachelor.model.MessageForAdmin;
import group.Food_order_bachelor.model.User;
import group.Food_order_bachelor.repository.MessageForAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageForAdminService implements MessageForAdminServiceInterface{
    private final MessageForAdminRepository messageForAdminRepository;
    private final RabbitTemplate rabbitTemplate;
    private final MessageForAdminAdapter messageForAdminAdapter = new MessageForAdminAdapter();
    private final Gson gson = new Gson();

    @Override
    public void createMessageForAdmin(CreateMessageForAdminDto dto, User manager) {
        MessageForAdmin messageForAdmin = messageForAdminAdapter.createMessageForAdminDtoToMessageForAdmin(dto,manager);
        messageForAdminRepository.save(messageForAdmin);
        sendToQueue(messageForAdmin);
    }

    @Override
    public List<ViewMessageForAdminDto> getMessages() {
        List<ViewMessageForAdminDto> dtos = new ArrayList<>();
        for(var message: messageForAdminRepository.getMessageForAdminThatAreNotReviewed()){
            dtos.add(messageForAdminAdapter.messageForAdminToViewMessageForAdminDto(message));
        }
        return dtos;
    }

    @Override
    public void changeStatusToReviewed(String messageId) {
        var message = messageForAdminRepository.getReferenceById(UUID.fromString(messageId));
        message.setMessageForAdminStatus(Message_for_admin_status.REVIEWED);
        messageForAdminRepository.saveAndFlush(message);
    }

    private void sendToQueue(MessageForAdmin messageForAdmin){
        ObjectMapper ow = new ObjectMapper();
        try{
            rabbitTemplate.convertAndSend("bachelor-exchange","delivery.message",
                    ow.writeValueAsString(messageForAdminAdapter.messageForAdminToViewMessageForAdminDto(messageForAdmin)));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
