package calculations.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistoryDTO {

    String operation;

    Integer result;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate createDate;

    String input;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDTO that = (HistoryDTO) o;
        return Objects.equals(operation, that.operation) &&
               Objects.equals(result, that.result) &&
               Objects.equals(createDate, that.createDate) &&
               Objects.equals(input, that.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, result, createDate, input);
    }
}
