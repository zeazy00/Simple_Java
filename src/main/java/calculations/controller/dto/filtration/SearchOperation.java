package calculations.controller.dto.filtration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

/*
Выражение для поиска значения по полю
с нужным HistoryFiltrationOption
 */
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchOperation {
    String fieldName;
    HistoryFiltrationOption option;
    Object fieldValue;

    public SearchOperation(FilterFields fieldName,
                           HistoryFiltrationOption option,
                           Object fieldValue) {
        this.fieldName = fieldName.getFieldName();
        this.option = option;
        this.fieldValue = fieldValue;
    }

    public boolean isFieldNameEquals(FilterFields fieldName){
        return this.fieldName.equals(fieldName.getFieldName());
    }
}
