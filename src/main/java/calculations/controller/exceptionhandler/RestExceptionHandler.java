package calculations.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.OperationNotSupportedException;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OperationNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String operationNotFoundHandler(OperationNotSupportedException ex) {
        return "Can't execute operation " + ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String exceptionHandler(Exception ex) {
        return ex.getMessage();
    }

}
