package com.example.formapi.mapper;

import com.example.formapi.domain.application.Section;
import com.example.formapi.dto.SectionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectionMapper {

    private final SectionFieldMapper sectionFieldMapper;

//    public Section toEntity(ReqSectionDto dto) {
//        return Section.builder()
//                .title(dto.getTitle())
//                .sectionFields(dto.getSectionFields().stream().map(sectionFieldMapper::toEntity).collect(Collectors.toList()))
//                .build();
//    }

    public SectionDto toDto(Section section) {
        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setTitle(section.getTitle());
        dto.setValidation(section.isValidation());
        dto.setTemplateId(section.getTemplate().getId());
        dto.setSectionFields(section.getSectionFields().stream().map(sectionFieldMapper::toDto).toList());
        return dto;
    }
}
