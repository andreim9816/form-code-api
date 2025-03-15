package com.example.formapi.mapper;

import com.example.formapi.domain.application.FormSection;
import com.example.formapi.dto.FormSectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class FormSectionMapper {

    private final FormSectionFieldMapper formSectionFieldMapper;

    public FormSectionDto toDto(FormSection formSection) {
        FormSectionDto dto = new FormSectionDto();
        dto.setId(formSection.getId());
        dto.setStatus(formSection.getStatus());
        dto.setFormSectionFields(formSection.getFormSectionFields().stream().map(formSectionFieldMapper::toDto).collect(toList()));
        return dto;
    }
}
