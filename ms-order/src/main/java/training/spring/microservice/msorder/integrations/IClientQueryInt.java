package training.spring.microservice.msorder.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.spring.microservice.msorder.integrations.models.Customer;

@FeignClient("CUSTOMER")
public interface IClientQueryInt {

    @GetMapping("/api/v1/customer/query/find/one")
    Customer findCustomer(@RequestParam String phone);

}
