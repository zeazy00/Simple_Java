package calculations.integration.rest;

import calculations.controller.dto.OperationResultDTO;
import calculations.controller.dto.ParticularOperationRequestDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static calculations.model.calculator.CalculationAvailableOperations.SUM;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Проверка ответа сервера
*/

@SpringBootTest
@AutoConfigureMockMvc
public class PostBodySumCalcRestIT {

    private final String url = "/math/calculate/particular";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void sumValidTest() throws Exception {

        //Arrange
        ParticularOperationRequestDTO requestDTO = new ParticularOperationRequestDTO("1454668",
                                                                                     SUM);
        ObjectMapper mapper = new ObjectMapper();
        String bodyJson = mapper.writeValueAsString(requestDTO);

        //act & assert
        String jsonString = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                  .contentType(APPLICATION_JSON)
                                                                  .content(bodyJson))
                                   .andExpect(status().isCreated())
                                   .andReturn()
                                   .getResponse()
                                   .getContentAsString();

        // assert
        OperationResultDTO resultDTO = mapper.readValue(jsonString, OperationResultDTO.class);

        Assertions.assertEquals("Sum", resultDTO.getOperationName());
        Assertions.assertEquals(34, resultDTO.getResult());
    }

    @Test
    void meaningOfLifeInputTest() throws Exception {
        //Arrange
        ParticularOperationRequestDTO requestDTO = new ParticularOperationRequestDTO("777777",
                                                                                     SUM);
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(requestDTO);

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(APPLICATION_JSON)
                                              .content(data))
               .andExpect(status().isNonAuthoritativeInformation());

    }

    @Test
    void badNumberInputTest() throws Exception {
        //arrange
        //Arrange
        ParticularOperationRequestDTO requestDTO = new ParticularOperationRequestDTO("3",
                                                                                     SUM);
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(requestDTO);

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(APPLICATION_JSON)
                                              .content(data))
               .andExpect(status().isNonAuthoritativeInformation());

    }
}
