package calculations.integration.db;


import calculations.controller.dto.filtration.Filtration;
import calculations.controller.dto.filtration.NotSupportedFilterException;
import calculations.controller.dto.filtration.SearchOperation;
import calculations.model.entity.MathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresDataTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static calculations.controller.dto.filtration.FilterFields.*;
import static calculations.controller.dto.filtration.HistoryFiltrationOption.*;
import static calculations.model.calculator.CalculationAvailableOperations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@EnablePostgresDataTest
public class RepositoryFiltrationIT {

    @Autowired
    private MathExpressionRepository repository;

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void nullFilterTest(){
        //arrange
        Filtration filtration = null;

        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(8);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByInput() {

        //arrange
        SearchOperation searchOperation = new SearchOperation(INPUT,
                                                              EQUALS,
                                                              "123456");
        Filtration filtration = new Filtration(searchOperation);

        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(5);
        assertThat(result).extracting("input")
                          .containsOnly("123456");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByDate() {
        //arrange
        LocalDate dateTime = LocalDate.parse("2021-07-12");
        SearchOperation searchOption = new SearchOperation(CREATE_DATE,
                                                           EQUALS,
                                                           dateTime);
        Filtration filtration = new Filtration(searchOption);

        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(1);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByOperation() {
        //arrange
        Filtration filtration = new Filtration(new SearchOperation(OPERATION,
                                                                   EQUALS,
                                                                   MAX));
        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(4);
        assertThat(result).extracting("operation")
                          .containsOnly(MAX);

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByResult() {
        //arrange
        Filtration filtration = new Filtration(new SearchOperation(RESULT,
                                                                   EQUALS,
                                                                   6));
        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(3);
        assertThat(result).extracting("result")
                          .containsOnly(6);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByInputAndOperation() {
        //arrange
        Filtration inputFiltration = new Filtration(new SearchOperation(INPUT,
                                                                        EQUALS,
                                                                        "123456"));
        Filtration operationFiltration = new Filtration(new SearchOperation(OPERATION,
                                                                            EQUALS,
                                                                            MAX));
        //act
        List<MathExpression> result = repository.findAll(inputFiltration.and(operationFiltration));

        //assert
        assertThat(result).size().isEqualTo(2);
        assertThat(result).extracting("input", "operation")
                          .containsOnly(tuple("123456", MAX));
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByDateAndOperation() {
        //arrange
        LocalDate date = LocalDate.of(2021, 6, 29);
        Filtration dateFilter = new Filtration(new SearchOperation(CREATE_DATE,
                                                                   EQUALS,
                                                                   date));

        Filtration operationFilter = new Filtration(new SearchOperation(OPERATION,
                                                                        EQUALS,
                                                                        AVG));

        //act
        List<MathExpression> result = repository.findAll(dateFilter.and(operationFilter));

        //assert
        assertThat(result).size().isEqualTo(1);

        MathExpression mathExpression = result.get(0);
        assertEquals(date, mathExpression.getCreateDate());
        assertEquals(AVG, mathExpression.getOperation());
        assertEquals(3, mathExpression.getResult());
        assertEquals("123456", mathExpression.getInput());
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findLessThanDate() {
        //arrange
        LocalDate dateTime = LocalDate.of(2021, 6, 30);
        Filtration filtration = new Filtration(new SearchOperation(CREATE_DATE,
                                                                   LESS_THAN,
                                                                   dateTime));

        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(3);

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findLessThanOperation() {
        //arrange
        Filtration filter = new Filtration(new SearchOperation(OPERATION,
                                                               LESS_THAN,
                                                               MAX));

        //act
        Exception exception = assertThrows(NotSupportedFilterException.class, () -> {
            List<MathExpression> result = repository.findAll(filter);
        });

        //assert
        assertEquals(exception.getMessage(), "This comparison filter is not supported");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findGreaterThanOperation() {
        //arrange
        Filtration filter = new Filtration(new SearchOperation(OPERATION,
                                                               GREATER_THAN,
                                                               MIN));

        //act
        Exception exception = assertThrows(NotSupportedFilterException.class, () -> {
            List<MathExpression> result = repository.findAll(filter);
        });

        //assert
        assertEquals(exception.getMessage(), "This comparison filter is not supported");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findGreaterAndLessThanDate() {
        //arrange
        LocalDate startDate = LocalDate.of(2021, 7, 1);
        Filtration greaterThanFiltration = new Filtration(new SearchOperation(CREATE_DATE,
                                                                              GREATER_THAN,
                                                                              startDate));
        LocalDate finalDate = LocalDate.of(2021, 7, 15);
        Filtration lessThanFiltration = new Filtration(new SearchOperation(CREATE_DATE,
                                                                           LESS_THAN,
                                                                           finalDate));

        //act
        List<MathExpression> result = repository.findAll(lessThanFiltration.and(greaterThanFiltration));

        //assert
        assertThat(result).size().isEqualTo(3);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findGreaterAndLessThanInput() {
        //arrange
        Integer startInput = 1;
        Integer endInput = 1000;
        Filtration greaterThanFiltration = new Filtration(new SearchOperation(INPUT,
                                                                              GREATER_THAN,
                                                                              startInput));
        Filtration lessThanFiltration = new Filtration(new SearchOperation(INPUT,
                                                                           LESS_THAN,
                                                                           endInput));

        //act
        Exception exception = assertThrows(NotSupportedFilterException.class, () -> {
            List<MathExpression> result = repository.findAll(greaterThanFiltration.and(lessThanFiltration));
        });

        //assert
        assertEquals(exception.getMessage(), "This comparison filter is not supported");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findContainsInInput() {
        //arrange
        Filtration filtration = new Filtration(new SearchOperation(INPUT,
                                                                   CONTAINS,
                                                                   "123"));

        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        assertThat(result).size().isEqualTo(5);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findContainsInResult() {
        //arrange
        Filtration filtration = new Filtration(new SearchOperation(RESULT,
                                                                   CONTAINS,
                                                                   "6"));

        //act
        Exception exception = assertThrows(NotSupportedFilterException.class, () -> {
            List<MathExpression> result = repository.findAll(filtration);
        });

        //assert
        assertEquals(exception.getMessage(), "This entry filter is not supported");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findContainsInDate() {

        //arrange
        Filtration filer = new Filtration(new SearchOperation(CREATE_DATE,
                                                              CONTAINS,
                                                              "12"));

        //act
        Exception exception = assertThrows(NotSupportedFilterException.class, () -> {
            List<MathExpression> result = repository.findAll(filer);
        });

        //assert
        assertEquals(exception.getMessage(), "This entry filter is not supported");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findContainsInOperation() {
        //arrange
        Filtration filer = new Filtration(new SearchOperation(OPERATION,
                                                              CONTAINS,
                                                              "M"));

        //act
        Exception exception = assertThrows(NotSupportedFilterException.class, () -> {
            List<MathExpression> result = repository.findAll(filer);
        });

        //assert
        assertEquals(exception.getMessage(), "This entry filter is not supported");
    }
}
