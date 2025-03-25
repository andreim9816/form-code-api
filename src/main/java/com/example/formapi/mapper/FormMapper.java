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
    private final TemplateMapper templateMapper;

    public FormDto toDto(Form form) {
        FormDto formDto = new FormDto();
        formDto.setId(form.getId());
        formDto.setCreatedDate(form.getCreatedDate());
        formDto.setFinishedDate(form.getFinishedDate());
        formDto.setCurrentValidationSectionId(form.getCurrentValidationSection() != null ? form.getCurrentValidationSection().getId() : null);
        formDto.setCurrentSectionId(form.getCurrentSection() != null ? form.getCurrentSection().getId() : null);
        formDto.setCurrentUserId(form.getCurrentUser() != null ? form.getCurrentUser().getId() : null);
        formDto.setCreatorUserId(form.getCreatorUser().getId());
        formDto.setTemplate(templateMapper.toDto(form.getTemplate()));
        formDto.setFormSections(form.getFormSections().stream().map(formSectionMapper::toDto).collect(toList()));
        return formDto;
    }
}
