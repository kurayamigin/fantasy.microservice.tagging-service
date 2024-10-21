package com.kurayamigin.fantasy.tagging_service.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.kurayamigin.fantasy")
@EnableReactiveMongoAuditing
@EnableMongoRepositories(basePackages = "com.kurayamigin.fantasy")
public class MongoConfig {

}
