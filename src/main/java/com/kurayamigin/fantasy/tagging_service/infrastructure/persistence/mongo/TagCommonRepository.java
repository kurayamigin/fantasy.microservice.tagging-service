package com.kurayamigin.fantasy.tagging_service.infrastructure.persistence.mongo;

import com.kurayamigin.fantasy.tagging_service.domain.models.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagCommonRepository extends CrudRepository<Tag, String>, MongoRepository<Tag, String> {
}
