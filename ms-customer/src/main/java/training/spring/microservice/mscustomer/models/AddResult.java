package training.spring.microservice.mscustomer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddResult {
    private String description;
    private String customerId;
}
