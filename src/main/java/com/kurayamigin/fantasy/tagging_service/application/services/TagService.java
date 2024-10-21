package com.kurayamigin.fantasy.tagging_service.application.services;

import com.kurayamigin.fantasy.rest.exceptions.ResourceNotFoundException;
import com.kurayamigin.fantasy.tagging_service.application.dtos.TagDto;
import com.kurayamigin.fantasy.tagging_service.application.mappers.TagMapper;
import com.kurayamigin.fantasy.tagging_service.domain.models.Tag;
import com.kurayamigin.fantasy.tagging_service.domain.repositories.FantasyReactiveRepository;
import com.kurayamigin.fantasy.tagging_service.infrastructure.persistence.mongo.TagRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TagService {
    private final FantasyReactiveRepository<Tag, String> tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public Flux<TagDto> get() {
        return tagRepository
                .findAll()
                .log()
                .map(tagMapper::toDto);
    }

    public Mono<TagDto> get(String id) {
        return tagRepository.findById(id)
                .log()
                .map(tagMapper::toDto);
    }

    public Mono<TagDto> save(TagDto tagDto) {
        Tag tag = tagMapper.toDocument(tagDto);
        return tagRepository.save(tag).map(tagMapper::toDto);
    }

    public Mono<TagDto> update(String id, TagDto tagDto) {
        return tagRepository.findById(id).map(tag -> {
            Tag newTag = tagMapper.toDocument(tagDto);
            newTag.setId(id);
            return newTag;
        }).flatMap(newTag -> tagRepository.save(newTag).map(tagMapper::toDto));
    }

    public Mono<TagDto> partialUpdate(String id, TagDto tagDto) {
        return tagRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("{error.resource.notFound}", "tag", id.toString())))
                .map(tag -> {
            tagMapper.patch(tagDto, tag);
            return tag;
        }).flatMap(newTag -> tagRepository.save(newTag).map(tagMapper::toDto));
    }
}
