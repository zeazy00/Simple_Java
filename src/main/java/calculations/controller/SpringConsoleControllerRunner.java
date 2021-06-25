package calculations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "test", havingValue = "false")
@RequiredArgsConstructor
public class SpringConsoleControllerRunner implements CommandLineRunner {

    private final CalculationController ctrl;

    @Override
    public void run(String... args) throws Exception {
        ctrl.start();
    }
}
