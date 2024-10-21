package com.kurayamigin.fantasy.tagging_service.infrastructure.middlewares;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Setter
@Getter
public class FantasyResponse<TBody> {

    private String microservice;

    private String timestamp;
    private HttpStatus status;
    private String message;
    private String path;

    private TBody body;

    protected FantasyResponse(String message, HttpStatus status, String path) {
        this.timestamp = Instant.now().toString();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    protected FantasyResponse() {
        this.timestamp = Instant.now().toString();
    }
}
