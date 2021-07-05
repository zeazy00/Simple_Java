package calculations.controller.exceptionhandler;

import calculations.model.validation.exceptions.PostProcessValidationException;
import calculations.model.validation.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.OperationNotSupportedException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OperationNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String operationNotFoundHandler(OperationNotSupportedException ex) {
        log.error(ex.getMessage());
        return "Can't execute operation " + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PostProcessValidationException.class)
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    String postProcessHandler(PostProcessValidationException ex){
        return "Operation result is invalid! " + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String preProcessHandler(ValidationException ex){
        return "Validation exception: " + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidInputException(IllegalArgumentException ex){
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String exceptionHandler(Exception ex) {
        log.error(ex.getMessage());
        return "";
    }

}
