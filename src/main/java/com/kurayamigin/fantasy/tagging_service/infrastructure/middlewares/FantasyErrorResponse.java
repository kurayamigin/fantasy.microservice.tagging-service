package com.kurayamigin.fantasy.tagging_service.infrastructure.middlewares;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
public class FantasyErrorResponse<T> extends FantasyResponse<T> {
    private Map<String, String> errors = new HashMap<>();

    protected FantasyErrorResponse() {
        super();
    }

    protected FantasyErrorResponse(String message, HttpStatus status, String path) {
        super(message, status, path);
    }

    protected FantasyErrorResponse(String message, HttpStatus status, String path, Map<String, String> errors) {
        super(message, status, path);
        this.setErrors(errors);
    }

}
