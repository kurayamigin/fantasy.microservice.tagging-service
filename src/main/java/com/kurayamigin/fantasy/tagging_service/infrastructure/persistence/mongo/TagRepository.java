package com.kurayamigin.fantasy.tagging_service.infrastructure.persistence.mongo;

import com.kurayamigin.fantasy.tagging_service.domain.models.Tag;
import com.kurayamigin.fantasy.tagging_service.domain.repositories.FantasyReactiveRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends FantasyReactiveRepository<Tag, String>, ReactiveMongoRepository<Tag, String> {
}

