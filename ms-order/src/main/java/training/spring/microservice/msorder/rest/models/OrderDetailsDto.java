package training.spring.microservice.msorder.rest.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    @NotNull
    @Size(min = 1)
    @Valid
    private List<MealDto> meals;
}
