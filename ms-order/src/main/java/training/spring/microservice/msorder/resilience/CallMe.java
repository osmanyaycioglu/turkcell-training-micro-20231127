package training.spring.microservice.msorder.resilience;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import training.spring.microservice.mscustomerapi.Customer;

@Component
public class CallMe {
    private int count  = 0;

    @Retry(name = "customerQuery",fallbackMethod = "testFallback")
    public Customer testSomething() {
        count++;
        if (count % 3 == 0){
            throw new IllegalStateException();
        }
        if (count > 30){
            throw new IllegalStateException();
        }
        return new Customer("osman",
                            "yay",
                            2D,
                            "7324876234",
                            "abc");
    }

    public Customer testFallback(Exception exp) {
        System.out.println("Fallback called.");
        return new Customer();
    }

}
