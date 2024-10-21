package com.kurayamigin.fantasy.tagging_service.infrastructure.middlewares;

import com.kurayamigin.fantasy.rest.validators.MessageResolvableError;
import com.kurayamigin.fantasy.rest.validators.ValidationException;
import com.kurayamigin.fantasy.tagging_service.utils.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseStatusExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception error, WebRequest request) {
        log.error(error.getMessage(), error);
        return FantasyResponseBuilder.<String>error()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setPath(RestUtils.getPath(request))
                .setBody(error.getMessage())
                .setErrors(error)
                .build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> noResourceFoundException(NoResourceFoundException error, WebRequest request) {
        log.error(error.getMessage(), error);
        return FantasyResponseBuilder.<String>error()
                .setStatus(HttpStatus.NOT_FOUND)
                .setPath(RestUtils.getPath(request))
                .setMessage(error.getMessage())
                .setErrors(error)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return FantasyResponseBuilder.<String>error()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setPath(RestUtils.getPath(request))
                .setErrors(ex.getBindingResult().getAllErrors().toArray(new ObjectError[0]))
                .setMessage(ex.getBody().getDetail())
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationExceptions(ValidationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return FantasyResponseBuilder.<String>error()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setPath(RestUtils.getPath(request))
                .setErrors(ex.getErrors().getErrors().toArray(new MessageResolvableError[0]))
                .setMessage(ex.getMessage())
                .build();
    }
}
