package training.spring.microservice.apigwcustomer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerFilter extends AbstractGatewayFilterFactory<LoggerFilter.Config> {

    public LoggerFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return (ex,fi)-> {
            System.out.println("Filter devreye girdi");
            return fi.filter(ex);
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private String level;
        private Boolean logIt;
    }



}
