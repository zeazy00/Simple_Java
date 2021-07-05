package calculations.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

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
        List operationResultList = mapper.readValue(jsonStringResult, List.class);

        Assertions.assertEquals(operationResultList.size(), 4);
    }
}
