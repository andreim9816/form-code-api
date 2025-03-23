package com.example.formapi.mapper;

import com.example.formapi.domain.application.Form;
import com.example.formapi.dto.FormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class FormMapper {
    private final FormSectionMapper formSectionMapper;

    public FormDto toDto(Form form) {
        FormDto formDto = new FormDto();
        formDto.setId(form.getId());
        formDto.setCreatedDate(form.getCreatedDate());
        formDto.setFinishedDate(form.getFinishedDate());
        formDto.setCurrentValidationSectionId(form.getCurrentValidationSection().getId());
        formDto.setCurrentSectionId(form.getCurrentSection().getId());
        formDto.setCurrentUserId(form.getCurrentUser().getId());
        formDto.setCreatorUserId(form.getCreatorUser().getId());
        formDto.setFormSections(form.getFormSections().stream().map(formSectionMapper::toDto).collect(toList()));
        return formDto;
    }
}
