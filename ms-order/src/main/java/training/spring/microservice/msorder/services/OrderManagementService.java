package training.spring.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training.spring.microservice.msorder.integrations.CustomerQueryIntegration;
import training.spring.microservice.msorder.integrations.models.Customer;
import training.spring.microservice.msorder.services.models.Order;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final CustomerQueryIntegration customerQueryIntegration;

    public void placeOrder(Order orderParam) {
        Customer customerLoc = customerQueryIntegration.getCustomer(orderParam.getPhoneNumber());
        Customer customerLoc2 = customerQueryIntegration.getCustomer2(orderParam.getPhoneNumber());
        Customer customerLoc3 = customerQueryIntegration.getCustomer3(orderParam.getPhoneNumber());
        System.out.println(customerLoc);
        System.out.println(customerLoc2);
        System.out.println(customerLoc3);
    }

}
