package training.spring.microservice.msorder;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import training.spring.microservice.mscommon.error.ErrorConfig;
import training.spring.microservice.mscommon.error.FeignConfig;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@Import({ErrorConfig.class,
         FeignConfig.class})
public class MsOrderApplication {

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }


    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class,
                                  args);
    }

}
