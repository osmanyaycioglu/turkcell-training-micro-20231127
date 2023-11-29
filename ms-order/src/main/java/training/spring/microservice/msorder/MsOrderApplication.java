package training.spring.microservice.msorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import training.spring.microservice.mscommon.error.ErrorConfig;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@Import(ErrorConfig.class)
public class MsOrderApplication {


    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class,
                                  args);
    }

}
