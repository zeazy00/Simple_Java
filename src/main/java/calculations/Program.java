package calculations;

import calculations.config.AppConfig;
import calculations.controller.CalculationController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
//@EnableConfigurationProperties(AppConfig.class)
public class Program {
    public static void main(String[] args) {
        ApplicationContext context = configureContext(args);

//        String opName = context.getBean(AppConfig.class).getService().getOperationName();
//        System.out.println("Operation name is " + opName);

        context.getBean(CalculationController.class).start();
    }

//    private static ApplicationContext configureContext(String[] args){
//        return SpringApplication.run(Program.class, args);
//    }

    private static ApplicationContext configureContext(String[] args){
        return new ClassPathXmlApplicationContext("beans.xml");
    }
}
