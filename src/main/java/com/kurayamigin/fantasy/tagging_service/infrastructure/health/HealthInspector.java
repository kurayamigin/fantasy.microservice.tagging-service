package com.kurayamigin.fantasy.tagging_service.infrastructure.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HealthInspector {
    private final ApplicationContext context;
    private final Map<String, HealthIndicator> healthIndicators;

    public HealthInspector(ApplicationContext context) {
        this.context = context;
        healthIndicators = context.getBeansOfType(HealthIndicator.class);
    }

    public void checkForHealthIndicators() throws UnhealthyServiceException {
        // Self-Injecting to achieve retry with AOP
        HealthInspector self = context.getBean(HealthInspector.class);

        // Iterate and print their class names and health status
        this.healthIndicators.forEach(self::check);
        System.out.println("Services are available.");
    }

    @Retryable(retryFor = UnhealthyServiceException.class)
    public void check(String bean, HealthIndicator healthIndicator) throws UnhealthyServiceException {
        System.out.println("Checking HealthIndicator Bean Name: " + bean);
        Health health = healthIndicator.health();
        if (!health.getStatus().equals(Status.UP))
            throw new UnhealthyServiceException(
                    String.format("Unavailable service on: %s, status: %s, more details: %s",
                            bean, health.getStatus(), health));
    }
}
