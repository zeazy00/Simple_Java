package calculations.integration;

import calculations.SimplJavaProgram;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AllArgsConstructor
public class CalculationRestControllerTest {

//    @Autowired
//    private WebTestClient webTestClient;

    @Test
    void sumTest() {
        WebTestClient webTestClient = WebTestClient.bindToServer()
                                                   .baseUrl("http://localhost:8080")
                                                   .build();

        //act
        String responce = webTestClient.post()
                                       .uri("/math/calculate/sum")
                                       .contentType(MediaType.APPLICATION_FORM_URLENCODED).bodyValue("input = 12544")
                                       .accept(MediaType.APPLICATION_JSON)
                                       .exchange()
                                       //assert
                                       .expectStatus()
                                       .isOk()
                                       .expectBody(String.class)
                                       .returnResult().getResponseBody();

        //assert
        Assertions.assertTrue(responce.contains("Sum"));
        Assertions.assertTrue(responce.contains("16"));
    }

}
