package calculations.integration;

import calculations.controller.dto.OperationResultDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sumTest() throws Exception {

        //Arrange
        String bodyJson = "{input:1454668}";
        Gson gson = new Gson();

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/math/calculate/sum")
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));
        //assert
        MvcResult result = resultActions.andExpect(
                MockMvcResultMatchers.status()
                                     .isCreated()).andReturn();

        String jsonString = result.getResponse().getContentAsString();
        OperationResultDTO resultDTO = gson.fromJson(jsonString, OperationResultDTO.class);

        Assertions.assertEquals(resultDTO.getOperationName(),"Sum");
        Assertions.assertEquals(resultDTO.getResult(),34);
    }

}
