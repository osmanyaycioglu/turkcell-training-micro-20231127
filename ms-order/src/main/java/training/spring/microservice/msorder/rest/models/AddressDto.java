package training.spring.microservice.msorder.rest.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotEmpty(message = "city boş olamaz")
    @NotBlank
    private String city;
    @NotEmpty
    @NotBlank
    private String street;
    @NotEmpty
    @NotBlank
    private String addressDesc;

}
