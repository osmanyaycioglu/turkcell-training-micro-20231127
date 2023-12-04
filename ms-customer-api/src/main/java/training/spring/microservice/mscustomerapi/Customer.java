package training.spring.microservice.mscustomerapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Customer {
    private String name;
    private String surname;
    private Double discount;
    private String phoneNumber;
    private String note;

}
