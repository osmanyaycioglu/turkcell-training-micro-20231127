package training.spring.microservice.msorder.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;
import training.spring.microservice.mscustomerapi.Customer;
import training.spring.microservice.msorder.integrations.models.AddResult;

@FeignClient(value = "CUSTOMER",contextId = "clientProvision")
public interface ICustomerProvisionInt {

    @PostMapping("/api/v1/customer/provision/add")
    public AddResult add(Customer customerParam);

}
