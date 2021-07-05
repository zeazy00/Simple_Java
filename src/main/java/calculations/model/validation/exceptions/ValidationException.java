package calculations.model.validation.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }
}
