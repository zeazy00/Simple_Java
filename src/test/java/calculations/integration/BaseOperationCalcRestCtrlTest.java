package calculations.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BaseOperationCalcRestCtrlTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void emptyInputTest() throws Exception{
        //Arrange
        String bodyJson = "";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/math/calculate/sum")
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));

        //assert
        resultActions.andExpect(
                MockMvcResultMatchers.status()
                                     .isBadRequest());
    }

    @Test
    void negativeNumberInputTest() throws Exception{
        //Arrange
        String bodyJson = "-4449";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/math/calculate/sum")
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));

        //assert
        resultActions.andExpect(
                MockMvcResultMatchers.status()
                                     .isBadRequest());
    }
}
