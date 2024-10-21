package com.kurayamigin.fantasy.tagging_service.domain.repositories;

import com.kurayamigin.fantasy.nosql_data.Document;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FantasyReactiveRepository<T extends Document<TKey>, TKey>
        extends ReactiveCrudRepository<T, TKey> {
}
