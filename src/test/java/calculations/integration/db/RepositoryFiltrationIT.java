package calculations.integration.db;


import calculations.controller.dto.filtration.HistorySearchOperation;
import calculations.model.entity.MathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static calculations.controller.dto.filtration.HistoryFiltrationOption.*;
import static org.springframework.data.jpa.domain.Specification.where;

import calculations.controller.dto.filtration.HistoryFiltration;
import org.springframework.data.jpa.domain.Specification;

@DBRider
@SpringBootTest
public class RepositoryFiltrationIT {

    @Autowired
    private MathExpressionRepository repository;

    @BeforeEach
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void setUp() { }

    @Test
    public void findEquatableByDate() {
        //arrange


        //act

        //assert

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void findEquatableByInput() {

        //arrange
        HistorySearchOperation searchOperation = new HistorySearchOperation("input",
                                                                EQUALS,
                                                                123456);
        HistoryFiltration filtration = new HistoryFiltration(searchOperation);

        //act
        List<MathExpression> result = repository.findAll(filtration);

        //assert
        Assertions.assertEquals(result.size(), 5);
        //TODO: add assertThat extraction - containsOnly input = 123456
    }

    @Test
    public void findEquatableByOperation() {

    }

    @Test
    public void findEquatableByResult() {

    }

    @Test
    public void findEquatableByDateAndOperation() {

    }

    @Test
    public void findLessThanDate() {

    }

    public void findLessThanOperation() {
        //TODO: exception on this case
    }

    public void findGreaterThanOperation() {
        //TODO: exception on this case
    }

    public void findGreaterAndLessThanDate() {

    }

    public void findGreaterAndLessThanInput() {

    }

    public void findContainsInInput() {

    }

    public void findContainsInResult() {

    }

    public void findContainsInDate() {

    }

    public void findContainsInOperation() {
        //TODO: exception on this case
    }
}
