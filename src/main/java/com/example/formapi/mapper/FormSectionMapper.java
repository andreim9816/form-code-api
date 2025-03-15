package com.example.formapi.mapper;

import com.example.formapi.domain.application.FormSection;
import com.example.formapi.dto.FormSectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FormSectionMapper {

    public FormSectionDto toDto(FormSection formSection) {
        FormSectionDto dto = new FormSectionDto();
        dto.setId(formSection.getId());
        dto.setStatus(formSection.getStatus());
        //todo formSectionFields;
        return dto;
    }
}
