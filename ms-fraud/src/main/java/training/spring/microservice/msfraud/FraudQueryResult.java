package training.spring.microservice.msfraud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraudQueryResult {
    private String phoneNumber;
    private String fraudType;

}

