package training.spring.microservice.msfraud;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/fraud/query")
public class FraudQueryController {

    @PostMapping("/check")
    public Mono<FraudQueryResult> check(FraudQueryRequest fraudQueryRequestParam) {
        return Mono.just(new FraudQueryResult(fraudQueryRequestParam.getPhoneNumber(),
                                              "none"));
    }
}
