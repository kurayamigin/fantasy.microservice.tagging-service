package com.kurayamigin.fantasy.tagging_service.interfaces.rest;

import com.kurayamigin.fantasy.tagging_service.application.dtos.TagDto;
import com.kurayamigin.fantasy.tagging_service.application.services.TagService;
import com.kurayamigin.fantasy.tagging_service.application.validators.TagValidator;
import com.kurayamigin.fantasy.tagging_service.utils.ReactiveUtils;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("tag")
public class TagController {

    private final static Logger log = LoggerFactory.getLogger(TagController.class);
    private final TagValidator tagValidator;
    private final TagService tagService;

    public TagController(TagValidator tagValidator, TagService tagService) {
        this.tagValidator = tagValidator;
        this.tagService = tagService;
    }

    @GetMapping
    public Flux<TagDto> get() {
        return tagService.get();
    }

    @GetMapping("{id}")
    public Mono<TagDto> get(@PathVariable String id) {
        return tagService.get(id);
    }

    @PostMapping
    public Mono<ResponseEntity<?>> create(@Valid @RequestBody TagDto dto) {
        return tagValidator.reactiveValidate(dto)
                .then(tagService.save(dto)
                                .map(doc -> ResponseEntity.created(URI.create("/tag/" + doc.getId())).body(doc)));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<?>> update(@PathVariable String id, @Valid @RequestBody TagDto dto) {
        return tagValidator.reactiveValidate(dto)
                .then(tagService.update(id, dto)
                        .map(doc -> ResponseEntity.noContent().build()));
    }

    @PatchMapping("{id}")
    public Mono<ResponseEntity<?>> patch(@PathVariable String id, @Valid @RequestBody TagDto dto) {
        return tagValidator.reactiveValidate(dto)
                .then(tagService.update(id, dto)
                        .map(doc -> ResponseEntity.created(URI.create("/tag/" + doc.getId())).body(doc)));
    }


}
