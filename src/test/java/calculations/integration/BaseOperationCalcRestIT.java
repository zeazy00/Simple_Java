package calculations.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseOperationCalcRestIT {

    @Autowired
    private MockMvc mockMvc;

    private String url = "/math/calculate/sum";

    @Test
    void emptyInputTest() throws Exception {
        //Arrange
        String bodyJson = "";

        //act & assert
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                                              .contentType(APPLICATION_JSON)
                                              .content(bodyJson))
               .andExpect(status().isBadRequest());

    }

    @Test
    void invalidInputTest() throws Exception {
        //Arrange
        String bodyJson = "45ds9e";

        //act & assert
        String  responseBody = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                                 .contentType(APPLICATION_JSON)
                                                                                 .content(bodyJson))
                                                  .andExpect(status().isBadRequest())
                                                  .andReturn()
                                                  .getResponse()
                                                  .getContentAsString();

        //assert
        Assertions.assertEquals("Input string must contain digits only",
                                responseBody);
    }

    @Test
    void negativeNumberInputTest() throws Exception {
        //Arrange
        String bodyJson = "-4449";

        //act & assert
        String responseBody = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                    .contentType(APPLICATION_JSON)
                                                                    .content(bodyJson))
                                     .andExpect(status().isBadRequest())
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString();

        //assert
        Assertions.assertEquals("Input string must contain digits only",
                                responseBody);
    }

    @Test
    void devilsNumberInputTest() throws Exception {
        //arrange
        String data = "154666478";

        //act & assert
        String responseBody = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                    .contentType(APPLICATION_JSON)
                                                                    .content(data))
                                     .andExpect(status().isBadRequest())
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString();

        //assert
        Assertions.assertEquals("Validation exception: Antichrist detected!!! Bonfire is waiting for you!",
                                responseBody);
    }

    @Test
    void longInputTest() throws Exception {
        //Arrange
        final int INPUT_STRING_SIZE = 100;

        StringBuilder data = new StringBuilder(INPUT_STRING_SIZE);
        for (int i = 1; i <= INPUT_STRING_SIZE; i++) {
            data.append(i % 10);
        }

        //Act & Assert
        String responseBody = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                    .contentType(APPLICATION_JSON)
                                                                    .content(data.toString()))
                                     .andExpect(status().isBadRequest())
                                     .andReturn()
                                     .getResponse()
                                     .getContentAsString();

        //Assert
        Assertions.assertEquals("Validation exception: Input number can't be bigger than 100 signs",
                                responseBody);
    }
}
