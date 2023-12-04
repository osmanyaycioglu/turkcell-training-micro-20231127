package training.spring.microservice.mscustomer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraudQueryRequest {
    private String phoneNumber;
    private String tcno;
    private String origin;
}
