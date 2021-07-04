package calculations.integration;

import calculations.controller.dto.OperationResultDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
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
        Gson gson = new Gson();

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(bodyJson));
        //assert
        MvcResult result = resultActions.andExpect(
                MockMvcResultMatchers.status()
                                     .isOk()).andReturn();

        String jsonString = result.getResponse().getContentAsString();
        OperationResultDTO resultDTO = gson.fromJson(jsonString, OperationResultDTO.class);

        Assertions.assertEquals(resultDTO.getOperationName(),"Sum");
        Assertions.assertEquals(resultDTO.getResult(),34);
    }

    @Test
    void meaningOfLifeInputTest() throws Exception {
        //Arrange
        String data = "777777";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(data));

        //assert
        resultActions.andExpect(MockMvcResultMatchers.status()
                                                     .isNonAuthoritativeInformation());
    }

    @Test
    void badNumberInputTest() throws Exception {
        //arrange
        String data = "3";

        //act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                            .contentType(MediaType.APPLICATION_JSON)
                                                                            .content(data));

        //assert
        resultActions.andExpect(MockMvcResultMatchers.status()
                                                     .isNonAuthoritativeInformation());
    }
}
