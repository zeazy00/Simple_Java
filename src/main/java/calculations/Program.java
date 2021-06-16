package calculations;

import calculations.controller.CalculationController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Program implements CommandLineRunner {

    CalculationController calculationController;

    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        calculationController.start();
    }
}
