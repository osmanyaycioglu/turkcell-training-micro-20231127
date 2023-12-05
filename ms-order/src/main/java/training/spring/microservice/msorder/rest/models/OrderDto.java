package training.spring.microservice.msorder.rest.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @NotEmpty
    @NotBlank
    @Size(min = 9)
    @Schema(description = "GSM Number must be without 0",example = "54345022310")
    private String phoneNumber;
    @NotEmpty
    @NotBlank
    @Size(min = 2,max = 15)
    private String firstName;
    @NotEmpty
    @NotBlank
    @Size(min = 3,max = 20)
    private String lastName;
    @NotNull
    @Valid
    private AddressDto address;
    @Valid
    @NotNull
    private OrderDetailsDto orderDetailsDto;

    private CustomerCredentialDto customerCredential;
}
