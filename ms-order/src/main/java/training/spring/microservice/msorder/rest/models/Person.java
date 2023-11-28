package training.spring.microservice.msorder.rest.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private String surname;
    private Integer weight;
    private Integer height;
}
