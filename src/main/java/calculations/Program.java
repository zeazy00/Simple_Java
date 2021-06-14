package calculations;

import calculations.controller.CalculationController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Program {
    public static void main(String[] args) {
//        SpringApplication.run(Program.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.getBean(CalculationController.class).start();
    }
}
