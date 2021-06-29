package calculations.runner;

import calculations.controller.CalculationConsoleController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "console", havingValue = "true")
@Profile("!test")
@RequiredArgsConstructor
public class SpringConsoleControllerRunner implements CommandLineRunner {

    private final CalculationConsoleController ctrl;

    @Override
    public void run(String... args) throws Exception {
        ctrl.start();
    }
}
