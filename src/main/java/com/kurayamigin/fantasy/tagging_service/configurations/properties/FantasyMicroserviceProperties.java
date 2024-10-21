package com.kurayamigin.fantasy.tagging_service.configurations.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("spring.fantasy.microservice")
public class FantasyMicroserviceProperties {
    private String name;

}
