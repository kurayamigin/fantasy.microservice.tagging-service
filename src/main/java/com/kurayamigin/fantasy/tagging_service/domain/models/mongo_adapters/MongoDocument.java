package com.kurayamigin.fantasy.tagging_service.domain.models.mongo_adapters;

import com.kurayamigin.fantasy.nosql_data.Document;
import org.springframework.data.annotation.Id;

public abstract class MongoDocument<TKey> extends Document<TKey> {
    @Id
    private TKey id;
}
