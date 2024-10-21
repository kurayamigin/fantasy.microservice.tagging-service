package com.kurayamigin.fantasy.tagging_service.domain.models.mongo_adapters;

import com.kurayamigin.fantasy.nosql_data.AuditableLogicDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class MongoAuditableLogicDocument<TKey> extends AuditableLogicDocument<TKey> {

}
