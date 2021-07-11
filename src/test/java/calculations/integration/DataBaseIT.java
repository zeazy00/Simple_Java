package calculations.integration;

import calculations.model.calculator.CalculationAvailableOperations;
import calculations.model.entity.MathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
public class DataBaseIT {

    MathExpressionRepository repository;

    @Test
    @DataSet(cleanAfter = true, cleanBefore = true)
    public void createRepos(){
        MathExpression saveRes = repository.save(new MathExpression("546", CalculationAvailableOperations.AVG, 5));

        Assertions.assertNotNull(repository.getById(saveRes.getId()));
    }
}
