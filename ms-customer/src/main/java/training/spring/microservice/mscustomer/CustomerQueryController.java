package training.spring.microservice.mscustomer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import training.spring.microservice.mscustomer.models.FraudQueryRequest;
import training.spring.microservice.mscustomer.models.FraudQueryResult;
import training.spring.microservice.mscustomerapi.Customer;
import training.spring.microservice.mscustomerapi.ICustomerQueryController;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@RestController
public class CustomerQueryController implements ICustomerQueryController {

    @Value("${server.port}")
    private String port;
    private int    count = 0;


    public Customer findCustomer(String phone) {
        count++;
        if ((count % 3) == 0) {
            throw new IllegalStateException("Test Error");
        }
        Random randomLoc = null;
        try {
            randomLoc = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException eParam) {
            throw new RuntimeException(eParam);
        }
        return Customer.builder()
                       .withName("osman" + randomLoc.nextInt())
                       .withSurname("yaycioglu")
                       .withPhoneNumber(phone)
                       .withDiscount(0.2D)
                       .withNote("Local port : " + port)
                       .build();
    }

}
