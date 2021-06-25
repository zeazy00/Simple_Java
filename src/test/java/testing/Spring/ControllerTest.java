package testing.Spring;

import calculations.Program;
import calculations.controller.CalculationController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = Program.class)
public class ControllerTest {

    @Autowired
    CalculationController ctrl;

    int[] data = {1, 14, 23, 44, 78, 96, 452};

    @Value("${calculation.min}")
    String minEnabled;

    @Value("${calculation.max}")
    String maxEnabled;

    @Value("${calculation.sum}")
    String sumEnabled;

    @Value("${calculation.avg}")
    String avgEnabled;

    @Test
    public void checkCreation(){
    }

    @Test
    public void checkExecutionSum() {
        String name = "Sum";
        Boolean isEnabled = stringToBool(sumEnabled);

        if(isEnabled){
            int res = ctrl.execute(name, data);

            Assertions.assertEquals(res, Arrays.stream(data).sum());
        }
        else{

            Exception exception = assertThrows(NullPointerException.class, () -> {
                ctrl.execute(name, data);
            });

            Assertions.assertNotNull(exception);
        }

    }

    @Test
    public void checkExecutionAvg(){
        String name = "Avg";

        Boolean isEnabled = stringToBool(avgEnabled);

        if(isEnabled){
            int res = ctrl.execute(name, data);

            Assertions.assertEquals(res, (int)Arrays.stream(data).average().getAsDouble());
        }
        else{

            Exception exception = assertThrows(NullPointerException.class, () -> {
                ctrl.execute(name, data);
            });

            Assertions.assertNotNull(exception);
        }

    }

    @Test
    @ConditionalOnProperty(name = "calculation.min", havingValue = "true")
    public void checkExecutionMin(){
        String name = "Min";
        Boolean isEnabled = stringToBool(minEnabled);

        if(isEnabled){
            int res = ctrl.execute(name, data);

            Assertions.assertEquals(res, Arrays.stream(data).min().getAsInt());
        }
        else{

            Exception exception = assertThrows(NoSuchElementException.class, () -> {
                ctrl.execute(name, data);
            });

            Assertions.assertNotNull(exception);
        }
    }

    @Test
    public void checkExecutionMax(){
        String name = "Max";
        Boolean isEnabled = stringToBool(maxEnabled);

        if(isEnabled){
            int res = ctrl.execute(name, data);

            Assertions.assertEquals(res, Arrays.stream(data).max().getAsInt());
        }
        else{

            Exception exception = assertThrows(NullPointerException.class, () -> {
                ctrl.execute(name, data);
            });

            Assertions.assertNotNull(exception);
        }
    }

    private boolean stringToBool(String s){
        if(s == null)
            return false;

        return s.equals("true");
    }
}
