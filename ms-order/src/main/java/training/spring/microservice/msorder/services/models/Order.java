package training.spring.microservice.msorder.services.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @NotEmpty
    @NotBlank
    @Size(min = 9)
    private String          phoneNumber;
    @NotEmpty
    @NotBlank
    @Size(min = 2,max = 15)
    private String          name;
    @NotEmpty
    @NotBlank
    @Size(min = 3,max = 20)
    private String          surname;
    @NotEmpty(message = "city boş olamaz")
    @NotBlank
    private String city;
    @NotEmpty
    @NotBlank
    private String street;
    @NotEmpty
    @NotBlank
    private String addressDesc;
    @Valid
    @NotNull
    private OrderDetails orderDetailsDto;

    private CustomerCredential customerCredential;

    private LocalDateTime orderDate;
    private LocalDateTime orderCloseDate;
    private LocalDateTime orderCancelDate;

}
