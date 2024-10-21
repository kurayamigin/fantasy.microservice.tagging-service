package com.kurayamigin.fantasy.tagging_service.infrastructure.middlewares;

import com.kurayamigin.fantasy.tagging_service.configurations.properties.FantasyMicroserviceProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class FantasyResponseBuilder<T,  B extends FantasyResponseBuilder<T, B>> implements ApplicationContextAware {

    protected static ApplicationContext context;

    protected FantasyResponse<T> response;

    protected B self() {
        return (B) this;
    }

    protected FantasyResponseBuilder() {
        this.response = new FantasyResponse<>();
        this.response.setTimestamp(Instant.now().toString());
    }

    @PostConstruct
    protected void init() throws NoSuchBeanDefinitionException {
        String microserviceName = context.getBean(FantasyMicroserviceProperties.class).getName();
        if (microserviceName == null) throw new ApplicationContextException("Microservice name not found in properties");
        this.response.setMicroservice(microserviceName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    @SuppressWarnings("unchecked")
    public static <T> FantasyResponseBuilder<T, ?> create() {
        return (FantasyResponseBuilder<T, ?>) context.getBean(FantasyResponseBuilder.class);
    }

    public static <T> FantasyErrorResponseBuilder<T> error() {
        return FantasyErrorResponseBuilder.create();
    }

    public B setStatus(HttpStatus status) {
        response.setStatus(status);
        return self();
    }

    public B setBody(T body) {
        response.setBody(body);
        return self();
    }

    public B setMessage(String message) {
        response.setMessage(message);
        return self();
    }

    public B setPath(String path) {
        response.setPath(path);
        return self();
    }

    public ResponseEntity<? extends FantasyResponse<T>> build() {
        return new ResponseEntity<>(this.response, this.response.getStatus());
    }

}
