package com.example.formapi.mapper;

import com.example.formapi.domain.application.SectionField;
import com.example.formapi.dto.SectionFieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectionFieldMapper {

    public SectionFieldDto toDto(SectionField sectionField) {
        SectionFieldDto dto = new SectionFieldDto();
        dto.setId(sectionField.getId());
        dto.setAddedDate(sectionField.getAddedDate());
        dto.setDefaultValue(sectionField.getDefaultValue());
        dto.setContentType(sectionField.getContentType());
        dto.setSectionId(sectionField.getSection().getId());
        return dto;
    }
}
