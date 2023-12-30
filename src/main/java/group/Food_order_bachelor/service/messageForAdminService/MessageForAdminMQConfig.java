package group.Food_order_bachelor.service.messageForAdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageForAdminMQConfig {
    static final String topicExchangeName = "bachelor-exchange";

    static final String queueName = "messages-for-admin";

    @Bean
    public Queue queue() {
        return new Queue(queueName,false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("delivery.#");
    }
}
