package calculations.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseOperationCalcRestCtrlTest {

    @Autowired
    protected MockMvc mockMvc;

    private String url = "/math/calculate/sum";

    @Test
    void emptyInputTest() throws Exception {
        //Arrange
        String bodyJson = "";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));

        //assert
        resultActions.andExpect(
                MockMvcResultMatchers.status()
                                     .isBadRequest());
    }

    @Test
    void invalidInputTest() throws Exception {
        //Arrange
        String bodyJson = "45ds9e";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));

        //assert
        MockHttpServletResponse response = resultActions.andExpect(MockMvcResultMatchers.status()
                                                                                        .isBadRequest())
                                                        .andReturn()
                                                        .getResponse();

        Assertions.assertEquals("Input string must contain digits only",
                                response.getContentAsString());
    }

    @Test
    void negativeNumberInputTest() throws Exception {
        //Arrange
        String bodyJson = "-4449";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));

        //assert
        MockHttpServletResponse response = resultActions.andExpect(MockMvcResultMatchers.status()
                                                                                        .isBadRequest())
                                                        .andReturn()
                                                        .getResponse();

        Assertions.assertEquals("Input string must contain digits only",
                                response.getContentAsString());
    }

    @Test
    void devilsNumberInputTest() throws Exception {
        //arrange
        String data = "154666478";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(data));

        //assert
        MockHttpServletResponse response = resultActions.andExpect(MockMvcResultMatchers.status()
                                                                                        .isBadRequest())
                                                        .andReturn()
                                                        .getResponse();

        Assertions.assertEquals("Validation exception: Antichrist detected!!! Bonfire is waiting for you!",
                                response.getContentAsString());
    }

    @Test
    void longInputTest() throws Exception {
        //Arrange
        final int INPUT_STRING_SIZE = 100;

        StringBuilder data = new StringBuilder(INPUT_STRING_SIZE);
        for (int i = 1; i <= INPUT_STRING_SIZE; i++) {
            data.append(i % 10);
        }

        //Act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(data.toString()));

        //Assert
        MockHttpServletResponse response = resultActions.andExpect(MockMvcResultMatchers.status()
                                                                                        .isBadRequest())
                                                        .andReturn().getResponse();

        Assertions.assertEquals("Validation exception: Input number can't be bigger than 100 signs",
                                response.getContentAsString());
    }
}
