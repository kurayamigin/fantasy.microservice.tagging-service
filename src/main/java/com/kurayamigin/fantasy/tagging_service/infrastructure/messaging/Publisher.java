package com.kurayamigin.fantasy.tagging_service.infrastructure.messaging;

import java.util.Map;

public interface Publisher {
    <TEvent> void publish(TEvent event);
    <TEvent> void publish(TEvent event, Map<String, Object> headers);
}
