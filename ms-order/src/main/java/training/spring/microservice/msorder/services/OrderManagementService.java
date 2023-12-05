package training.spring.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import training.spring.microservice.mscustomerapi.Customer;
import training.spring.microservice.msorder.integrations.CustomerProvisionIntegration;
import training.spring.microservice.msorder.integrations.CustomerQueryIntegration;
import training.spring.microservice.msorder.integrations.models.EMessageType;
import training.spring.microservice.msorder.integrations.models.Message;
import training.spring.microservice.msorder.services.models.Order;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final CustomerQueryIntegration     customerQueryIntegration;
    private final CustomerProvisionIntegration customerProvisionIntegration;
    private final RabbitTemplate               rabbitTemplate;

    public void placeOrder(Order orderParam) {

        Customer customerLoc = null;
        try {
            customerLoc = customerQueryIntegration.getCustomer(orderParam.getPhoneNumber());
        } catch (Exception eParam) {
            System.out.println("Problem anında yapılacak şeyler");
            throw eParam;
        }
        Customer customerLoc2 = customerQueryIntegration.getCustomer2(orderParam.getPhoneNumber());
        Customer customerLoc3 = customerQueryIntegration.getCustomer3(orderParam.getPhoneNumber());
        System.out.println(customerLoc);
        System.out.println(customerLoc2);
        System.out.println(customerLoc3);
    }

    public void placeOrder1(Order orderParam) {
        Customer customerLoc = null;
        try {
            customerLoc = customerQueryIntegration.getCustomer(orderParam.getPhoneNumber());
        } catch (Exception eParam) {
            System.out.println("Problem anında yapılacak şeyler");
            throw eParam;
        }
    }

    public void placeOrder2(Order orderParam) {
        Customer customerLoc = null;
        try {
            customerLoc = customerQueryIntegration.getCustomer3(orderParam.getPhoneNumber());
            customerProvisionIntegration.addCustomer(customerLoc);

            Message messageLoc = new Message(orderParam.getPhoneNumber(),
                                             "Siparişiniz alındı-" + UUID.randomUUID()
                                                                         .toString(),
                                             EMessageType.SMS);
            rabbitTemplate.convertAndSend("message-t-exchange",
                                          "send.sms.order.istanbul",
                                          messageLoc);
        } catch (Exception eParam) {
            System.out.println("Problem anında yapılacak şeyler");
            throw eParam;
        }
    }

}
