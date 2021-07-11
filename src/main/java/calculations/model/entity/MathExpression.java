package calculations.model.entity;

import calculations.model.calculator.CalculationAvailableOperations;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MathExpression extends BaseEntity {

    @NotNull
    @NotBlank
    @Column(nullable = false)
    String input;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    CalculationAvailableOperations operation;

    @NotNull
    @Column(nullable = false)
    Integer result;

    @NotNull
    @Column(nullable = false, updatable = false)
    LocalDateTime createDate;

    public MathExpression(String input,
                          CalculationAvailableOperations operation,
                          Integer result) {

        this.input = input;
        this.operation = operation;
        this.result = result;
    }

    @PrePersist
    private void prePersist() {
        createDate = LocalDateTime.now();
    }

}
