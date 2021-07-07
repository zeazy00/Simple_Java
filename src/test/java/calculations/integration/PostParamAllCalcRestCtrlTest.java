package calculations.integration;

import calculations.controller.dto.OperationResultDTO;
import calculations.model.calculator.CalculationAvailableOperations;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static calculations.model.calculator.CalculationAvailableOperations.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostParamAllCalcRestCtrlTest {

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
                                         .andExpect(status().isOk())
                                         .andReturn()
                                         .getResponse()
                                         .getContentAsString();

        //Assert
        List<OperationResultDTO> operationResultList = mapper.readValue(jsonStringResult,
                                                                        new TypeReference<List<OperationResultDTO>>() {});

        Assertions.assertEquals(operationResultList.size(), 4);

        OperationResultDTO sumResult = getResult(operationResultList, SUM);
        Assertions.assertEquals(sumResult.getResult(), 44);

        OperationResultDTO maxResult = getResult(operationResultList, MAX);
        Assertions.assertEquals(maxResult.getResult(), 9);

        OperationResultDTO minResult = getResult(operationResultList, MIN);
        Assertions.assertEquals(minResult.getResult(), 2);

        OperationResultDTO avgResult = getResult(operationResultList, AVG);
        Assertions.assertEquals(avgResult.getResult(), 5);
    }

    private OperationResultDTO getResult(List<OperationResultDTO> source, CalculationAvailableOperations operatioName) {
        return source.stream()
                     .filter(x -> x.getOperationName()
                                   .equals(operatioName.getOpName()))
                     .findFirst()
                     .get();
    }
}
