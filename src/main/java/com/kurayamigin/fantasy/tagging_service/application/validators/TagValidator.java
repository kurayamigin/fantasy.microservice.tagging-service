package com.kurayamigin.fantasy.tagging_service.application.validators;

import com.kurayamigin.fantasy.rest.validators.FantasyValidator;
import com.kurayamigin.fantasy.tagging_service.application.dtos.TagDto;
import com.kurayamigin.fantasy.tagging_service.domain.models.Tag;
import com.kurayamigin.fantasy.tagging_service.infrastructure.persistence.mongo.TagRepository;
import com.kurayamigin.fantasy.tagging_service.utils.ReactiveUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TagValidator extends FantasyValidator<TagDto> {
    private final TagRepository tagRepository;

    public TagValidator(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected void validation(TagDto dto) {
        if (tagExists(dto)) {
            errors.reject("error.tag.alreadyExists", "translation fails here.");
        }
    }

    private Boolean tagExists(TagDto dto) {
        Tag tag = new Tag();
        tag.setTag(dto.getTag());

        Example<Tag> tagExample = Example.of(tag, ExampleMatcher.matchingAny()
                .withIgnoreCase("tag")
                .withIgnorePaths("description", "active", "updatedDate"));

        return tagRepository.exists(tagExample).block();
    }

    public Mono<Void> reactiveValidate(TagDto dto) {
        return ReactiveUtils.reactiveBlocking(
                () -> super.validate(dto)
        );
    }
}
