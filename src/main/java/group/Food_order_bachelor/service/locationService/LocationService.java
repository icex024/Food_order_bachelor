package group.Food_order_bachelor.service.locationService;

import group.Food_order_bachelor.dto.coordinates.Coordinates;
import group.Food_order_bachelor.service.openRouteService.OpenRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService implements LocationServiceInterface{

    private final OpenRouteService openRouteService;
    private final RabbitTemplate rabbitTemplate;
    private final AmqpAdmin amqpAdmin;

    @Override
    public void start(Coordinates start, Coordinates finish,String id) {
        String topicExchangeName = "spring-boot-exchange";
        Queue queue= new Queue(id,false);
        TopicExchange topicExchange = new TopicExchange(topicExchangeName);
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with("foo.bar.#");
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(topicExchange);
        amqpAdmin.declareBinding(binding);
        //List<Coordinates> route = openRouteService.getRoute(start,finish);
        //for(var r:route ){
            rabbitTemplate.convertAndSend(topicExchangeName,"foo.bar.baz","test for multiple users");
        //}
    }

    @Override
    public void deleteQueue() {
        amqpAdmin.purgeQueue("spring-boot");
    }


}
