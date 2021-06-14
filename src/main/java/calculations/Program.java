package calculations;

import calculations.controller.CalculationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Program {
    public static void main(String[] args) {
        ApplicationContext context = configureContext(args);

        context.getBean(CalculationController.class).start();
    }

    private static ApplicationContext configureContext(String[] args){
        return SpringApplication.run(Program.class, args);
    }

//    private static ApplicationContext configureContext(String[] args){
//        return new ClassPathXmlApplicationContext("beans.xml");
//    }
}
