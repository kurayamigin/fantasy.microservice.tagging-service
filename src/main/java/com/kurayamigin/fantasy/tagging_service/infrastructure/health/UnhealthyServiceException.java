package com.kurayamigin.fantasy.tagging_service.infrastructure.health;

public class UnhealthyServiceException extends RuntimeException {
    public UnhealthyServiceException(String message) {
        super(message);
    }

    public UnhealthyServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
