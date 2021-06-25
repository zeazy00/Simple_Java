package calculations;

import calculations.controller.CalculationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Program {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Program.class, args);
        context.getBean(CalculationController.class).start();
        //не стал использовать implements CommandLineRunner и
        //вызов CalculationController.start() в  @Override run
        //потому что тогда на тестах этот метод тоже вызывается
        //а этого мне не надо
    }
}
