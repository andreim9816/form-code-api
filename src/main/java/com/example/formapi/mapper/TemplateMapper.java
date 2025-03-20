package com.example.formapi.mapper;

import com.example.formapi.domain.application.Section;
import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.dto.input.ReqSectionDto;
import com.example.formapi.dto.input.ReqSectionFieldDto;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.exception.CustomException;
import com.example.formapi.repository.application.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TemplateMapper {

    private final CompanyRepository companyRepository;
    private final SectionMapper sectionMapper;
    private final SectionFieldMapper sectionFieldMapper;

    public TemplateDto toDto(Template template) {
        TemplateDto dto = new TemplateDto();
        dto.setId(template.getId());
        dto.setTitle(template.getTitle());
        dto.setDescription(template.getDescription());
        dto.setCompanyId(template.getCompany().getId());
        dto.setCreatorUserId(template.getCreatorUser() != null ? template.getCreatorUser().getId() : null); //todo
        dto.setSections(template.getSections().stream().map(sectionMapper::toDto).collect(Collectors.toList()));
        return dto;
    }

    @Transactional
    public Template toEntity(Long companyId, ReqTemplateDto dto) {
        var template = new Template();
        var sections = new ArrayList<Section>();

        template.setTitle(dto.getTitle());
        template.setDescription(dto.getDescription());
        template.setCompany(companyRepository.getReferenceById(companyId));

        for (ReqSectionDto sectionDto : dto.getSections()) {
            var section = sectionMapper.toEntity(sectionDto);
            section.setTemplate(template); // FK

            var sectionFields = new ArrayList<SectionField>();
            for (ReqSectionFieldDto sectionFieldDto : sectionDto.getSectionFields()) {
                var sectionField = sectionFieldMapper.toEntity(sectionFieldDto);
                sectionFields.add(sectionField);
            }
            section.setSectionFields(sectionFields);
            sections.add(section);
        }
        template.setSections(sections);
        if (!template.getSections().getLast().isValidation()) {
            throw new CustomException("Last section is not a validation one");
        }

        return template;
    }
}

