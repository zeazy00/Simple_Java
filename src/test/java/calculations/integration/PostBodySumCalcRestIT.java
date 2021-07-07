package calculations.integration;

import calculations.controller.dto.OperationResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostBodySumCalcRestIT {

    private final String url = "/math/calculate/sum";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void sumValidTest() throws Exception {

        //Arrange
        String bodyJson = "1454668";
        ObjectMapper mapper = new ObjectMapper();

        //act & assert
        String jsonString = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                  .contentType(APPLICATION_JSON)
                                                                  .content(bodyJson))
                                   .andExpect(status().isOk())
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
        String data = "777777";

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(APPLICATION_JSON)
                                              .content(data))
               .andExpect(status().isNonAuthoritativeInformation());

    }

    @Test
    void badNumberInputTest() throws Exception {
        //arrange
        String data = "3";

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(APPLICATION_JSON)
                                              .content(data))
               .andExpect(status().isNonAuthoritativeInformation());

    }
}
