package com.kurayamigin.fantasy.tagging_service.cross_cutting.retry;

import com.kurayamigin.fantasy.rest.retry.RetryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
@EnableConfigurationProperties(RetryProperties.class)
public class RetryConfig {


    private final RetryLogger retryLogger;
    private final RetryProperties retryProperties;


    public RetryConfig(RetryLogger retryLogger, RetryProperties retryProperties) {
        this.retryLogger = retryLogger;
        this.retryProperties = retryProperties;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(retryProperties.getMaxAttempts());
        retryTemplate.setRetryPolicy(retryPolicy);

        // Configure the backoff policy (e.g., 2 seconds delay)
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(retryProperties.getBackOff());
        retryTemplate.setBackOffPolicy(backOffPolicy);

        retryTemplate.registerListener(retryLogger);
        return retryTemplate;
    }
}
