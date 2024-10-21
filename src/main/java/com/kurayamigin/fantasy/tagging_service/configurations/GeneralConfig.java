package com.kurayamigin.fantasy.tagging_service.configurations;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "com.kurayamigin.fantasy.tagging_service.configurations.properties")
public class GeneralConfig {
}
