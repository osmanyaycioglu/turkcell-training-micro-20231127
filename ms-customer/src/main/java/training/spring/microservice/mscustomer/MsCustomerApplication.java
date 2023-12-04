package training.spring.microservice.mscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import training.spring.microservice.mscommon.error.ErrorConfig;

@SpringBootApplication
@Import(ErrorConfig.class)
public class MsCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCustomerApplication.class, args);
	}

}
