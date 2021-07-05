package calculations.integration;

import calculations.controller.dto.OperationResultDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PostBodySumCalcRestCtrlTest extends BaseOperationCalcRestCtrlTest {

    private String url = "/math/calculate/sum";

    @Test
    void sumValidTest() throws Exception {

        //Arrange
        String bodyJson = "1454668";
        ObjectMapper mapper = new ObjectMapper();

        //act & assert
        String jsonString = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                  .contentType(MediaType.APPLICATION_JSON)
                                                                  .content(bodyJson))
                                   .andExpect(MockMvcResultMatchers.status()
                                                                   .isOk())
                                   .andReturn()
                                   .getResponse()
                                   .getContentAsString();

        // assert
        OperationResultDTO resultDTO = mapper.readValue(jsonString, OperationResultDTO.class);

        Assertions.assertEquals(resultDTO.getOperationName(), "Sum");
        Assertions.assertEquals(resultDTO.getResult(), 34);
    }

    @Test
    void meaningOfLifeInputTest() throws Exception {
        //Arrange
        String data = "777777";

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(data))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNonAuthoritativeInformation());

    }

    @Test
    void badNumberInputTest() throws Exception {
        //arrange
        String data = "3";

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(data))
               .andExpect(MockMvcResultMatchers.status()
                                               .isNonAuthoritativeInformation());

    }
}
