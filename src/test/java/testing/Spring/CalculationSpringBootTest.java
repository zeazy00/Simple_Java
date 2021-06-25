package testing.Spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import calculations.Program;

@SpringBootTest(classes = Program.class)
public class CalculationSpringBootTest {

    @Test
    void contextLoads() {
    }

}
