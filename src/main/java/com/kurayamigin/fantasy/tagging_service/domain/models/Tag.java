package com.kurayamigin.fantasy.tagging_service.domain.models;

import com.kurayamigin.fantasy.tagging_service.domain.models.mongo_adapters.MongoAuditableLogicDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("tags")
public class Tag extends MongoAuditableLogicDocument<String> {
    private String tag;
    private String description;
}
