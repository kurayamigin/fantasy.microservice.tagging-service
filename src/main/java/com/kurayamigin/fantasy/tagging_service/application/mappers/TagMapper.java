package com.kurayamigin.fantasy.tagging_service.application.mappers;

import com.kurayamigin.fantasy.rest.mappers.DocumentMapper;
import com.kurayamigin.fantasy.rest.mappers.DocumentPatcher;
import com.kurayamigin.fantasy.tagging_service.application.dtos.TagDto;
import com.kurayamigin.fantasy.tagging_service.domain.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper extends
        DocumentMapper<Tag, TagDto, String>,
        DocumentPatcher<Tag, TagDto, String> {

    @Override
    Tag toDocument(TagDto dto);

    @Override
    TagDto toDto(Tag tag);

    @Override
    List<Tag> toDocument(List<TagDto> list);

    @Override
    List<Tag> toDto(List<Tag> list);

    @Override
    void patch(TagDto dto, @MappingTarget Tag tag);
}