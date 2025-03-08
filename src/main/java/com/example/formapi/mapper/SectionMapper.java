package com.example.formapi.mapper;

import com.example.formapi.domain.application.Section;
import com.example.formapi.dto.SectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectionMapper {

    private final SectionFieldMapper sectionFieldMapper;

    public SectionDto toDto(Section section) {
        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setTitle(section.getTitle());
        dto.setAutoValidated(section.getAutoValidated());
        dto.setTemplateId(section.getTemplate().getId());
        dto.setSectionFields(section.getSectionFields().stream().map(sectionFieldMapper::toDto).toList());
        return dto;
    }
}
