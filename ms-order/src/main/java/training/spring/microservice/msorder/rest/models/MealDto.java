package training.spring.microservice.msorder.rest.models;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDto {
    @NotEmpty
    @NotBlank
    private String mealName;
    @DecimalMin("1.0")
    private Double amount;
}
