package training.spring.microservice.mscustomer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/customer/query")
public class CustomerQueryController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/find/one")
    public Customer findCustomer(@RequestParam String phone) throws Exception {
        Random randomLoc = SecureRandom.getInstance("SHA1PRNG");
        return Customer.builder()
                       .withName("osman" + randomLoc.nextInt())
                       .withSurname("yaycioglu")
                       .withPhoneNumber(phone)
                       .withDiscount(0.2D)
                       .withNote("Local port : " + port)
                       .build();
    }

}
