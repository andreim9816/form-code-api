package com.example.formapi.mapper;

import com.example.formapi.domain.application.FormSectionField;
import com.example.formapi.dto.FormSectionFieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FormSectionFieldMapper {

    public FormSectionFieldDto toDto(FormSectionField entity) {
        FormSectionFieldDto dto = new FormSectionFieldDto();
        dto.setId(entity.getId());
        dto.setFormSectionId(entity.getFormSection().getId());
        dto.setSectionFieldId(entity.getSectionField().getId());
        dto.setBreakLine(entity.isBreakLine());
        dto.setContentStringId(entity.getContentStringId());
        dto.setContentBooleanId(entity.getContentBooleanId());
        dto.setContentDateId(entity.getContentDateId());
        dto.setContentNumberId(entity.getContentNumberId());

        return dto;
    }
}
