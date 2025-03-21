package com.example.formapi.mapper;

import com.example.formapi.domain.application.Section;
import com.example.formapi.dto.SectionDto;
import com.example.formapi.dto.SectionLiteDto;
import com.example.formapi.dto.input.ReqSectionDto;
import com.example.formapi.service.CompanyRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class SectionMapper {

    private final SectionFieldMapper sectionFieldMapper;
    private final CompanyRoleService companyRoleService;

    public Section toEntity(ReqSectionDto sectionDto) {
        var section = new Section();
        section.setTitle(sectionDto.getTitle());
        section.setValidation(sectionDto.isValidation());
        section.setCompanyRoles(sectionDto.getCompanyRoleIds().stream().map(companyRoleService::findById).collect(toList()));
        return section;
    }

    public SectionDto toDto(Section section) {
        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setTitle(section.getTitle());
        dto.setValidation(section.isValidation());
        dto.setTemplateId(section.getTemplate().getId());
        dto.setSectionFields(section.getSectionFields().stream().map(sectionFieldMapper::toDto).toList());
        return dto;
    }

    public SectionLiteDto toLiteDto(Section section) {
        SectionLiteDto dto = new SectionLiteDto();
        dto.setId(section.getId());
        dto.setTitle(section.getTitle());
        dto.setValidation(section.isValidation());
        return dto;
    }
}
