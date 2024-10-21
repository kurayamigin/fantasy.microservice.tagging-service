package com.kurayamigin.fantasy.tagging_service.application.dtos;

import com.kurayamigin.fantasy.rest.models.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagDto extends Dto<String> {
    @NotBlank(message = "{error.tag.name.blank}")
    private String tag;

    @Length(max = 200, message = "{error.tag.description.maxLength}")
    private String description;


}
