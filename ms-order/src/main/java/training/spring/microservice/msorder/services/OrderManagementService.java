package training.spring.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training.spring.microservice.mscustomerapi.Customer;
import training.spring.microservice.msorder.integrations.CustomerProvisionIntegration;
import training.spring.microservice.msorder.integrations.CustomerQueryIntegration;
import training.spring.microservice.msorder.services.models.Order;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final CustomerQueryIntegration     customerQueryIntegration;
    private final CustomerProvisionIntegration customerProvisionIntegration;

    public void placeOrder(Order orderParam) {

        Customer customerLoc  = null;
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
        Customer customerLoc  = null;
        try {
            customerLoc = customerQueryIntegration.getCustomer(orderParam.getPhoneNumber());
        } catch (Exception eParam) {
            System.out.println("Problem anında yapılacak şeyler");
            throw eParam;
        }
    }

    public void placeOrder2(Order orderParam) {
        Customer customerLoc  = null;
        try {
            customerLoc = customerQueryIntegration.getCustomer3(orderParam.getPhoneNumber());
            customerProvisionIntegration.addCustomer(customerLoc);
        } catch (Exception eParam) {
            System.out.println("Problem anında yapılacak şeyler");
            throw eParam;
        }
    }

}
