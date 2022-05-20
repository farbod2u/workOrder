package de.god.workorder.exception.handler;

import de.god.workorder.exception.WorkOrderValidationException;
import de.god.workorder.exception.model.ExceptionModel;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Saeed Safaeian
 */
@ControllerAdvice
@Log4j2
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {WorkOrderValidationException.class})
    public ResponseEntity<Object> handleRequestException(RuntimeException ex) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        ExceptionModel exception = new ExceptionModel(ex.getMessage(), LocalDateTime.now(), status);
        log.log(Level.ERROR, ex);

        return new ResponseEntity<>(exception, status);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus stat = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        ExceptionModel exception = new ExceptionModel(ex.getMessage(), LocalDateTime.now(), stat);
        log.log(Level.ERROR, ex);

        return new ResponseEntity<>(exception, stat);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus stat = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionModel exception = new ExceptionModel(ex.getMessage(), LocalDateTime.now(), stat);
        log.log(Level.ERROR, ex);

        return new ResponseEntity<>(exception, stat);
    }
}
