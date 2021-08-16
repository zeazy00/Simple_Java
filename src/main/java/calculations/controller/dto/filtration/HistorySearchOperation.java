package calculations.controller.dto.filtration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.PrimitiveIterator;

/*
Выражение для поиска значения по полю
с нужным HistoryFiltrationOption
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HistorySearchOperation {
    String fieldName;
    HistoryFiltrationOption option;
    Object fieldValue;
}
