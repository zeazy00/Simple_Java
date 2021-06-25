package calculations.model.outputprovider;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class ConsoleOutput implements OutputProvider {
    @Getter
    private final static ConsoleOutput instance = new ConsoleOutput();

    private ConsoleOutput() { }

    @Override
    public void output(String message) {
        System.out.print(message);
    }
}
