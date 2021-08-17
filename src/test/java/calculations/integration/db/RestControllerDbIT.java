package calculations.integration.db;

import calculations.controller.dto.ParticularOperationRequestDTO;
import calculations.model.entity.MathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.jupiter.tools.spring.test.web.annotation.EnableRestTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static calculations.model.calculator.CalculationAvailableOperations.SUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Db entity creation test via Rest request
*/

@EnableRestTest
@EnablePostgresIntegrationTest
public class RestControllerDbIT {

    private final String url = "/math/calculate/particular";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MathExpressionRepository repository;

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true)
    public void checkSuccessfulCreation() throws Exception {
        //arrange
        String numberInput = "45463";
        ParticularOperationRequestDTO requestDTO = new ParticularOperationRequestDTO(numberInput, SUM);
        ObjectMapper mapper = new ObjectMapper();
        String input = mapper.writeValueAsString(requestDTO);

        //act
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                                 .content(input))
               //assert
               .andExpect(status().isCreated());

        //assert
        List<MathExpression> allMathExpressions = repository.findAll();
        assertThat(allMathExpressions).size().isEqualTo(1);

        List<MathExpression> dbMathExpressions = repository.findByInput(numberInput);

        assertThat(dbMathExpressions).size().isEqualTo(1);
        MathExpression mathExpression = dbMathExpressions.get(0);

        assertEquals(numberInput,
                     mathExpression.getInput());

        assertEquals(22,
                     mathExpression.getResult());

        assertEquals(LocalDateTime.now().getDayOfMonth(),
                     mathExpression.getCreateDate()
                                   .getDayOfMonth());

        assertTrue(mathExpression.getId()
                                 .toString()
                                 .matches("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})"));
    }
}
