package calculations.model.validation.exceptions;

public class PostProcessValidationException extends RuntimeException {

    public PostProcessValidationException(){
        super();
    }

    public PostProcessValidationException(String message){
        super(message);
    }

}
