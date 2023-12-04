package training.spring.microservice.mscustomerapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICustomerQueryController {

    @GetMapping("/api/v1/customer/query/find/one")
    Customer findCustomer(@RequestParam String phone);

}