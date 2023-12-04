package training.spring.microservice.mscommon.error;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "microservice.local.info")
public class ErrorConfiguration {
    private String boundedContext;
    private String microservice;
}
