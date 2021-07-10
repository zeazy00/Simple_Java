package calculations.controller.exceptionhandler;

import calculations.model.validation.exceptions.PostProcessValidationException;
import calculations.model.validation.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(OperationNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String operationNotFoundHandler(OperationNotSupportedException ex) {
        log.error(ex.getMessage());
        return "Can't execute operation " + ex.getMessage();
    }

    @ExceptionHandler(PostProcessValidationException.class)
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    String postProcessHandler(PostProcessValidationException ex){
        return "Operation result is invalid! " + ex.getMessage();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String preProcessHandler(ValidationException ex){
        return "Validation exception: " + ex.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidInputException(IllegalArgumentException ex){
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String exceptionHandler(Exception ex) {
        log.error(ex.getMessage());
        return "";
    }

}
