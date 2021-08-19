package calculations.controller.dto.filtration.standard;

import calculations.model.calculator.CalculationAvailableOperations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/*
Фильтрация по заданию с фиксированными операциями - равенство, меньше/больше, входит
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FiltrationArgs {
    String input;

    CalculationAvailableOperations operation;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate initialDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate finalDate;
}
