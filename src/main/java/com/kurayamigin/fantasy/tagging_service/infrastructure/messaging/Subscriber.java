package com.kurayamigin.fantasy.tagging_service.infrastructure.messaging;

public interface Subscriber {
    void consume(String message);
}
