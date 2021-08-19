package calculations.integration.db.filtration.standard;


import calculations.controller.dto.OperationResultDTO;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.jupiter.tools.spring.test.web.annotation.EnableRestTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static calculations.model.calculator.CalculationAvailableOperations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableRestTest
@EnablePostgresIntegrationTest
public class HistoryViewRestControllerIT {

    private static final String getAllUrl = "/math/calculate/history";
    private static final String getFilterUrl = "/math/calculate/history/filter";

    @Autowired
    private MockMvc mockMvc;

    private static Stream<Arguments> filtrationParamTestSource() {
        LinkedMultiValueMap<String, String> inputLikeParams = new LinkedMultiValueMap<String, String>() {{
            add("input", "123");
        }};
        List<OperationResultDTO> inputLikeExpected = new ArrayList<OperationResultDTO>() {{
            add(new OperationResultDTO(MAX.getOpName(), 6));
            add(new OperationResultDTO(MIN.getOpName(), 1));
            add(new OperationResultDTO(SUM.getOpName(), 21));
            add(new OperationResultDTO(AVG.getOpName(), 3));
            add(new OperationResultDTO(MAX.getOpName(), 6));
        }};

        LinkedMultiValueMap<String, String> operationEqParams = new LinkedMultiValueMap<String, String>() {{
            add("operation", SUM.toString());
        }};
        List<OperationResultDTO> operationEqExpected = new ArrayList<OperationResultDTO>() {{
            add(new OperationResultDTO(SUM.getOpName(), 21));
        }};

        LinkedMultiValueMap<String, String> dateFromParams = new LinkedMultiValueMap<String, String>() {{
            add("initialDate", LocalDate.of(2021, 7, 12)
                                        .toString());
        }};
        List<OperationResultDTO> dateFromExpected = new ArrayList<OperationResultDTO>() {{
            add(new OperationResultDTO(MAX.getOpName(), 6));
            add(new OperationResultDTO(MAX.getOpName(), 8));
            add(new OperationResultDTO(MIN.getOpName(), 1));
        }};

        LinkedMultiValueMap<String, String> julyDatesParams = new LinkedMultiValueMap<String, String>() {{
            add("initialDate", LocalDate.of(2021, 7, 1)
                                        .toString());
            add("finalDate", LocalDate.of(2021, 7, 31)
                                      .toString());
        }};
        List<OperationResultDTO> julyDatesExpected = new ArrayList<OperationResultDTO>() {{
            add(new OperationResultDTO(MAX.getOpName(), 6));
            add(new OperationResultDTO(MAX.getOpName(), 8));
            add(new OperationResultDTO(MIN.getOpName(), 1));
            add(new OperationResultDTO(MAX.getOpName(), 9));
            add(new OperationResultDTO(SUM.getOpName(), 21));
        }};

        LinkedMultiValueMap<String, String> fullFilter = new LinkedMultiValueMap<String, String>() {{
            add("input", "3438");
            add("operation", MAX.toString());
            add("initialDate", LocalDate.of(2021, 7, 1)
                                        .toString());
            add("finalDate", LocalDate.of(2021, 7, 31)
                                      .toString());
        }};
        List<OperationResultDTO> fullExpected = new ArrayList<OperationResultDTO>() {{
            add(new OperationResultDTO(MAX.getOpName(), 8));
            add(new OperationResultDTO(MAX.getOpName(), 9));
        }};

        return Stream.of(
                Arguments.of(inputLikeParams, inputLikeExpected),
                Arguments.of(operationEqParams, operationEqExpected),
                Arguments.of(dateFromParams, dateFromExpected),
                Arguments.of(julyDatesParams, julyDatesExpected),
                Arguments.of(fullFilter, fullExpected)
                        );
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void getAllNotEmptyDb() throws Exception {
        //arrange
        ObjectMapper mapper = new ObjectMapper();

        //act
        String allDataJson = mockMvc.perform(get(getAllUrl))
                                    .andExpect(status().isOk())
                                    .andReturn()
                                    .getResponse()
                                    .getContentAsString();

        //assert
        List<OperationResultDTO> resultDto = mapper.readValue(allDataJson, new TypeReference<List<OperationResultDTO>>() {});
        assertThat(resultDto).size().isEqualTo(8);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_empty.json")
    public void getAllEmptyDb() throws Exception {
        //arrange
        ObjectMapper mapper = new ObjectMapper();

        //act
        String allDataJson = mockMvc.perform(get(getAllUrl))
                                    .andExpect(status().isOk())
                                    .andReturn()
                                    .getResponse()
                                    .getContentAsString();

        //assert
        List<OperationResultDTO> resultDto = mapper.readValue(allDataJson, new TypeReference<List<OperationResultDTO>>() {});
        assertThat(resultDto).size().isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("filtrationParamTestSource")
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void filtrationTest(LinkedMultiValueMap<String, String> reqParams, List<OperationResultDTO> expected) throws Exception {
        //arrange
        ObjectMapper mapper = new ObjectMapper();

        //act
        String filterDataResult = mockMvc.perform(get(getFilterUrl)
                                                          .params(reqParams))
                                         .andExpect(status().isOk())
                                         .andReturn()
                                         .getResponse()
                                         .getContentAsString();

        //assert
        List<OperationResultDTO> resultDto = mapper.readValue(filterDataResult, new TypeReference<List<OperationResultDTO>>() {});

        assertThat(resultDto).hasSameElementsAs(expected);
    }
}
