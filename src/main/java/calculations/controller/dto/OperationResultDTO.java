package calculations.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationResultDTO {

    String operation;

    Integer result;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationResultDTO resultDTO = (OperationResultDTO) o;
        return Objects.equals(operation, resultDTO.operation) && Objects.equals(result, resultDTO.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, result);
    }
}
