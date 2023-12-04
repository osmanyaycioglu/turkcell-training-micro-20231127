package training.spring.microservice.mscustomer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MyFluxController {

    @GetMapping("/test1")
    public Mono<String> getString(){
        return Mono.just("deneme");
    }
}
