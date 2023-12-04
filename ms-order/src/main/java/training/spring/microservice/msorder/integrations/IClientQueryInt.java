package training.spring.microservice.msorder.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.spring.microservice.mscustomerapi.ICustomerQueryController;

@FeignClient(value = "CUSTOMER",contextId = "clientQuery")
public interface IClientQueryInt extends ICustomerQueryController {

}
