package calculations.integration.db.filtration.flex;

import calculations.controller.ComplexHistoryViewRestController;
import calculations.controller.dto.OperationResultDTO;
import calculations.controller.dto.filtration.flex.SearchOperation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.jupiter.tools.spring.test.web.annotation.EnableRestTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static calculations.controller.dto.filtration.flex.FilterFields.*;
import static calculations.controller.dto.filtration.flex.HistoryFiltrationOption.*;
import static calculations.model.calculator.CalculationAvailableOperations.MAX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@EnableRestTest
@EnablePostgresIntegrationTest
public class RestMyFiltrationIT {
    private final String url = "/math/calculate/history";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComplexHistoryViewRestController ctrl;

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void explicitGetHistoryCallWithEmptyFilter() {
        //arrange
        SearchOperation[] filters = new SearchOperation[0];

        //act
        List<OperationResultDTO> resultDTOS = ctrl.getFilteredPage(filters);

        //assert
        assertThat(resultDTOS).size().isEqualTo(8);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void explicitGetHistoryCallWithOperationEqualsFilter() {
        //arrange
        SearchOperation[] filters = new SearchOperation[1];
        filters[0] = new SearchOperation(OPERATION, EQUALS, MAX);

        //act
        List<OperationResultDTO> resultDTOS = ctrl.getFilteredPage(filters);

        //assert
        assertThat(resultDTOS).size().isEqualTo(4);
        assertThat(resultDTOS).extracting("operationName")
                              .containsOnly(MAX.getOpName());
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void explicitGetHistoryCallWithMultipleFilters() {
        //arrange
        SearchOperation[] filters = new SearchOperation[3];
        filters[0] = new SearchOperation(OPERATION, EQUALS, MAX);
        filters[1] = new SearchOperation(RESULT, EQUALS, 6);
        filters[2] = new SearchOperation(INPUT, CONTAINS, "123");

        //act
        List<OperationResultDTO> resultDTOS = ctrl.getFilteredPage(filters);

        //assert
        assertThat(resultDTOS).size().isEqualTo(2);
        assertThat(resultDTOS).extracting("operationName", "result")
                              .containsOnly(tuple(MAX.getOpName(), 6));

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void emptyFiltration() throws Exception {
        //arrange
        SearchOperation[] filters = new SearchOperation[0];

        ObjectMapper mapper = new ObjectMapper();
        String filterStr = mapper.writeValueAsString(filters);

        //act
        String resultJson = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
                                                    .content(filterStr))
                                   .andExpect(status().isOk())
                                   .andReturn()
                                   .getResponse()
                                   .getContentAsString();

        //assert
        List<OperationResultDTO> resultDTOS = mapper.readValue(resultJson, new TypeReference<List<OperationResultDTO>>() {});
        assertThat(resultDTOS).size().isEqualTo(8);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void operationEqualsFiltration() throws Exception {
        //arrange
        SearchOperation[] filters = new SearchOperation[1];
        filters[0] = new SearchOperation(OPERATION, EQUALS, MAX);

        ObjectMapper mapper = new ObjectMapper();
        String filterStr = mapper.writeValueAsString(filters);

        //act
        String resultJson = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
                                                    .content(filterStr))
                                   .andExpect(status().isOk())
                                   .andReturn()
                                   .getResponse()
                                   .getContentAsString();

        //assert
        List<OperationResultDTO> resultDTOS = mapper.readValue(resultJson, new TypeReference<List<OperationResultDTO>>() {});
        assertThat(resultDTOS).size().isEqualTo(4);
        assertThat(resultDTOS).extracting("operationName")
                              .containsOnly(MAX.getOpName());
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "dbsets/filtration_db_test.json")
    public void datesComplexFiltration() throws Exception {
        //arrange
        SearchOperation[] filters = new SearchOperation[2];
        filters[0] = new SearchOperation(CREATE_DATE, GREATER_THAN, "2021-07-01");
        filters[1] = new SearchOperation(CREATE_DATE, LESS_THAN, "2021-07-31");

        ObjectMapper mapper = new ObjectMapper();
        String filterStr = mapper.writeValueAsString(filters);

        //act
        String resultJson = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)
                                                    .content(filterStr))
                                   .andExpect(status().isOk())
                                   .andReturn()
                                   .getResponse()
                                   .getContentAsString();

        //assert
        List<OperationResultDTO> resultDTOS = mapper.readValue(resultJson, new TypeReference<List<OperationResultDTO>>() {});
        assertThat(resultDTOS).size().isEqualTo(5);
    }

}
