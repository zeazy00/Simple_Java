package calculations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "test", havingValue = "false")
public class Runner implements CommandLineRunner {

    @Autowired
    CalculationController ctrl;

    @Override
    public void run(String... args) throws Exception {
        ctrl.start();
    }
}
