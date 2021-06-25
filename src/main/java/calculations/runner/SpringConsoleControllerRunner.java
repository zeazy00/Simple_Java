package calculations.runner;

import calculations.controller.CalculationController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class SpringConsoleControllerRunner implements CommandLineRunner {

    private final CalculationController ctrl;

    @Override
    public void run(String... args) throws Exception {
        ctrl.start();
    }
}
