package calculations.controller.dto.filtration.flex;

public class NotSupportedFilterException extends RuntimeException {
    public NotSupportedFilterException() {
        super("This filter operation is not supported!");
    }

    public NotSupportedFilterException(String message) {
        super(message);
    }
}
