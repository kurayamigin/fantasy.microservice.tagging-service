package com.kurayamigin.fantasy.tagging_service.cross_cutting.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class RetryLogger implements RetryListener {
    private static final Logger log = LoggerFactory.getLogger(RetryLogger.class);

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.warn("Retry attempt #{} failed due to: {}", context.getRetryCount(), throwable.getMessage());
    }
}
