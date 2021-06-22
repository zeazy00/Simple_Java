package calculations;

import calculations.controller.CalculationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@ConditionalOnProperty(value = "test", havingValue = "false")
public class Program implements CommandLineRunner {

    @Autowired
    CalculationController calculationController;

    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
//        ApplicationContext context = SpringApplication.run(Program.class, args);
//        context.getBean(CalculationController.class).start();
        //не стал использовать implements CommandLineRunner и
        //вызов CalculationController.start() в  @Override run
        //потому что тогда на тестах этот метод тоже вызывается
        //а этого мне не надо
    }

    @Override
    public void run(String... args) throws Exception {
        calculationController.start();
    }
}
