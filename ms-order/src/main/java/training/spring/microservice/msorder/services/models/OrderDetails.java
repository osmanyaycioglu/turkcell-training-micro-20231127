package training.spring.microservice.msorder.services.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.spring.microservice.msorder.rest.models.MealDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @NotNull
    @Size(min = 1)
    @Valid
    private List<Meal> meals;
}
