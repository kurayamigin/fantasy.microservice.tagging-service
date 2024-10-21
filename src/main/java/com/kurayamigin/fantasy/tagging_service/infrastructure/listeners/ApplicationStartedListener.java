package com.kurayamigin.fantasy.tagging_service.infrastructure.listeners;

import com.kurayamigin.fantasy.tagging_service.infrastructure.health.HealthInspector;
import com.kurayamigin.fantasy.tagging_service.infrastructure.health.UnhealthyServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    private static final Logger log = LoggerFactory.getLogger(ApplicationStartedListener.class);
    private final HealthInspector healthInspector;

    public ApplicationStartedListener(HealthInspector healthInspector) {
        this.healthInspector = healthInspector;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            log.info("Logging readiness...");
            System.out.println("Checking readiness");
            healthInspector.checkForHealthIndicators();
        } catch (UnhealthyServiceException unhealthyServiceException) {
            throw new IllegalStateException(unhealthyServiceException.getMessage());
        }
    }
}
