package calculations.integration.db;

import calculations.controller.dto.ParticularOperationRequestDTO;

import calculations.model.entity.MathExpression;
import calculations.model.repository.MathExpressionRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static calculations.model.calculator.CalculationAvailableOperations.SUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
        ParticularOperationRequestDTO requestDTO = new ParticularOperationRequestDTO("45463", SUM);
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

        List<MathExpression> dbMathExpressions = repository.findByInput(input);

        assertThat(dbMathExpressions).size().isEqualTo(1);
        MathExpression mathExpression = dbMathExpressions.get(0);

        assertEquals(input,
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
