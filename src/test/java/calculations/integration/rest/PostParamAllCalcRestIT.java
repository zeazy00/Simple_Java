package calculations.integration.rest;

import calculations.controller.dto.OperationResultDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static calculations.model.calculator.CalculationAvailableOperations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostParamAllCalcRestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calcAllTest() throws Exception {

        //Arrange
        String input = "64968524";
        ObjectMapper mapper = new ObjectMapper();


        //Act & Assert
        String jsonStringResult = mockMvc.perform(MockMvcRequestBuilders.post("/math/calculate")
                                                                        .param("input", input))
                                         .andExpect(status().isCreated())
                                         .andReturn()
                                         .getResponse()
                                         .getContentAsString();

        //Assert
        List<OperationResultDTO> operationResultList = mapper.readValue(jsonStringResult,
                                                                        new TypeReference<List<OperationResultDTO>>() {});

        assertThat(operationResultList).size().isEqualTo(4);

        assertThat(operationResultList).extracting("Result", "OperationName")
                                       .containsOnly(tuple(44, SUM.getOpName()),
                                                     tuple(5, AVG.getOpName()),
                                                     tuple(2, MIN.getOpName()),
                                                     tuple(9, MAX.getOpName()));

    }

}
