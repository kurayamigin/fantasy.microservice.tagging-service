package com.kurayamigin.fantasy.tagging_service.infrastructure.middlewares;

import com.kurayamigin.fantasy.rest.validators.MessageResolvableError;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class FantasyErrorResponseBuilder<T> extends FantasyResponseBuilder<T, FantasyErrorResponseBuilder<T>> {

    private FantasyErrorResponseBuilder() {
        super();
        this.response = new FantasyErrorResponse<>();
        this.response.setTimestamp(Instant.now().toString());
    }


    @SuppressWarnings("unchecked")
    public static <T> FantasyErrorResponseBuilder<T> create() {
        return (FantasyErrorResponseBuilder<T>) context.getBean(FantasyErrorResponseBuilder.class);
    }

    public FantasyErrorResponseBuilder<T> setErrors(Map<String, String> errors) {
        ((FantasyErrorResponse<T>)super.response).setErrors(errors);
        return this;
    }

    public FantasyErrorResponseBuilder<T> setErrors(ObjectError... errors) {
        Map<String, String> map = new HashMap<>();
        for (ObjectError error : errors)
            if (error instanceof FieldError fieldError)
                map.put(fieldError.getField(), error.getDefaultMessage());
            else
                map.put(error.getObjectName(), error.getDefaultMessage());
        return this.setErrors(map);
    }

    public FantasyErrorResponseBuilder<T> setErrors(MessageResolvableError... errors) {
        Map<String, String> map = new HashMap<>();
        for (MessageResolvableError error : errors)
                map.put(error.getObjectName(), error.getResolvedMessage() == null ? error.getDefaultMessage() : error.getResolvedMessage());
        return this.setErrors(map);
    }

    public FantasyErrorResponseBuilder<T> setErrors(Exception... exceptions) {
        Map<String, String> map = new HashMap<>();
        for (Exception exception : exceptions)
            map.put(exception.getClass().getSimpleName(), exception.getMessage());
        return this.setErrors(map);
    }

    @Override
    public ResponseEntity<? extends FantasyErrorResponse<T>> build() {
        return new ResponseEntity<>((FantasyErrorResponse<T>) super.response, super.response.getStatus());
    }


}

//    public ResponseEntity<FantasyErrorResponse> error(Exception exception, HttpStatus status, WebRequest request) {
//        FantasyErrorResponse response = new FantasyErrorResponse();
//        response.setStatus(status.value());
//        response.setErrors(exception);
//        response.setMessage(exception.getMessage());
//        response.setPath(request.getDescription(false).replace("uri=", ""));
//        return new ResponseEntity<>(response, status);
//    }
//
//    public ResponseEntity<FantasyErrorResponse> error(List<ObjectError> errors, HttpStatus status, WebRequest request) {
//        FantasyErrorResponse response = new FantasyErrorResponse();
//        response.setStatus(status.value());
//        response.setErrors(errors.toArray(new ObjectError[0]));
//        response.setPath(request.getDescription(false).replace("uri=", ""));
//        return new ResponseEntity<>(response, status);


