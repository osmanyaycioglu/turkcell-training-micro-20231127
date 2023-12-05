package training.spring.microservice.apigwcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApigwCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigwCustomerApplication.class,
                              args);
    }

}
