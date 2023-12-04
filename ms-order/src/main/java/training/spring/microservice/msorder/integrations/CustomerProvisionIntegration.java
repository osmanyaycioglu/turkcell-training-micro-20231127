package training.spring.microservice.msorder.integrations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training.spring.microservice.mscustomerapi.Customer;
import training.spring.microservice.msorder.integrations.models.AddResult;

@Service
@RequiredArgsConstructor
public class CustomerProvisionIntegration {
    private final ICustomerProvisionInt customerProvision;

    public AddResult addCustomer(Customer customerParam){
        return customerProvision.add(customerParam);
    }

}
