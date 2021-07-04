package calculations.model.validation.exceptions;

public class MathOperationNotSupportedException extends Exception {
    public MathOperationNotSupportedException() {
        super();
    }

    public MathOperationNotSupportedException(String message) {
        super(message);
    }
}
