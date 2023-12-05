package training.spring.microservice.apigwcustomer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
public class TestPredicate extends AbstractRoutePredicateFactory<TestPredicate.Config> {

    public TestPredicate() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(final Config config) {
        return swe -> {
            System.out.println("Predicate queried...");
            HttpHeaders headersLoc = swe.getRequest()
                                        .getHeaders();
            List<String> stringsLoc = headersLoc.get("xyz");
            if (stringsLoc != null && !stringsLoc.isEmpty()){
                String stringLoc = stringsLoc.get(0);
                if (stringsLoc.equals(config.getTestStr())){
                    return false;
                }
            }
            return true;
        };
    }

    @Data
    @Valid
    public static class Config {
        @NotEmpty
        private String testStr;
        @NotNull
        private Boolean doIt;
    }


}
