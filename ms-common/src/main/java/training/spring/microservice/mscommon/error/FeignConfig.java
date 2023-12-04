package training.spring.microservice.mscommon.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public GneralErrorDecoder gneralErrorDecoder(){
        return new GneralErrorDecoder();
    }

}
