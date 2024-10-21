package com.kurayamigin.fantasy.tagging_service.infrastructure.middlewares;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class FantasyErrorAttributes implements ErrorAttributes {

    @Value("${spring.application.name:null}")
    protected String microservice;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions e) {
        Map<String, Object> errorAttributes = new HashMap<>();
        Throwable error = getError(webRequest);
        errorAttributes.put("timestamp", Instant.now().toString());
        errorAttributes.put("message", error != null ? error.getMessage() : "Unknown error");
        errorAttributes.put("path", webRequest.getDescription(false).replace("uri=", ""));
        errorAttributes.put("microservice", this.microservice);
        return errorAttributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return (Throwable) webRequest.getAttribute("javax.servlet.error.exception", WebRequest.SCOPE_REQUEST);
    }
}
