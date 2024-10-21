package com.kurayamigin.fantasy.tagging_service.infrastructure.health;

import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class DbHealthIndicator implements HealthIndicator {

    private final static Logger log = LoggerFactory.getLogger(DbHealthIndicator.class);
    private final ReactiveMongoTemplate mongoTemplate;

    public DbHealthIndicator(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Health health() {
        try {
            // Block to ensure this is checked synchronously for the HealthIndicator
            MongoDatabase db = mongoTemplate.getMongoDatabase().block();
            Mono.from(Objects.requireNonNull(db).runCommand(new Document("ping", 1)))
                    .block();
            log.info("db is healthy");
            return Health.up().withDetail("Database", "MongoDB is available").build();
        } catch (Exception e) {
            log.error("db is unhealthy", e);
            return Health.down(e).withDetail("Error", e.getMessage()).build();
        }
    }
}
