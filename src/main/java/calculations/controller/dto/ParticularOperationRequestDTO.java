package calculations.controller.dto;

import calculations.model.calculator.CalculationAvailableOperations;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ParticularOperationRequestDTO {
    String numberInput;

    CalculationAvailableOperations operation;
}
