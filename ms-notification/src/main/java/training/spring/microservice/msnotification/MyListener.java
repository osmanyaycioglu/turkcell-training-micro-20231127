package training.spring.microservice.msnotification;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {


    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "sms-t-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-t-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "send.sms.#"))
    public void handleSMS(Message message) {
        System.out.println("Received sms : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "email-t-queue",
            autoDelete = "false",
            durable = "true"),
            exchange = @Exchange(name = "message-t-exchange",
                    autoDelete = "false",
                    durable = "true",
                    type = ExchangeTypes.TOPIC),
            key = "send.email.#"))
    public void handleEMAIL(Message message) {
        System.out.println("Received email : " + message);
    }

}
