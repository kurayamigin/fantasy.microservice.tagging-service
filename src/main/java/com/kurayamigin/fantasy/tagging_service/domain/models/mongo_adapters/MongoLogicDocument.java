package com.kurayamigin.fantasy.tagging_service.domain.models.mongo_adapters;

import com.kurayamigin.fantasy.nosql_data.LogicDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class MongoLogicDocument<TKey> extends LogicDocument<TKey> {
    @Id
    private TKey id;
}
