package calculations.integration;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PostParamAllCalcRestCtrlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calcAllTest() throws Exception {

        //Arrange
        String input = "64968524";
        Gson gson = new Gson();


        //Act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/math/calculate")
                                                                            .param("input", input));

        //Assert
        MvcResult result = resultActions.andExpect(MockMvcResultMatchers.status()
                                                                        .isOk())
                                        .andReturn();
        String jsonStringResult = result.getResponse().getContentAsString();
        List operationResultList = gson.fromJson(jsonStringResult, List.class);

        Assertions.assertEquals(operationResultList.size(), 4);
    }
}
