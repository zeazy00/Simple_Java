package calculations.model.validation.exceptions;

public class MathOperationNotSupportedException extends RuntimeException {
    public MathOperationNotSupportedException() {
        super();
    }

    public MathOperationNotSupportedException(String message) {
        super(message);
    }
}
