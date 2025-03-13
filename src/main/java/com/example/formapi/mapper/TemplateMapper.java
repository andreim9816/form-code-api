package com.example.formapi.mapper;

import com.example.formapi.domain.application.Section;
import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.dto.input.ReqSectionDto;
import com.example.formapi.dto.input.ReqSectionFieldDto;
import com.example.formapi.dto.input.ReqTemplateDto;
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
    private final DateValidatorMapper dateValidatorMapper;
    private final NumberValidatorMapper validatorMapper;
    private final TextValidatorMapper textValidatorMapper;
    private final NumberValidatorMapper numberValidatorMapper;

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

    //without builder works, to check !!
    @Transactional
    public Template toEntity(ReqTemplateDto dto) {
        var template = new Template();
        var sections = new ArrayList<Section>();

        template.setTitle(dto.getTitle());
        template.setDescription(dto.getDescription());
        template.setCompany(companyRepository.getReferenceById(dto.getCompanyId()));

        for (ReqSectionDto sectionDto : dto.getSections()) {
            var section = new Section();
            section.setTitle(dto.getTitle());
            section.setTemplate(template); // FK

            var sectionFields = new ArrayList<SectionField>();
            for (ReqSectionFieldDto sectionFieldDto : sectionDto.getSectionFields()) {
                var sectionField = new SectionField();
                sectionField.setContentType(sectionFieldDto.getContentType());
//                sectionField.setAddedDate(new Date());

                String defaultValue = "";
                switch (sectionFieldDto.getContentType()) {
                    case DATE -> {
                        defaultValue = sectionFieldDto.getContentDate().getValue().toString();
                        sectionField.setDateValidator(dateValidatorMapper.toEntity(sectionFieldDto.getDateValidator()));
                    }
                    case NUMBER -> {
                        defaultValue = "" + sectionFieldDto.getContentNumber().getValue();
                        sectionField.setNumberValidator(numberValidatorMapper.toEntity(sectionFieldDto.getNumberValidator()));
                    }
                    case STRING -> {
                        defaultValue = sectionFieldDto.getContentString().getValue();
                        sectionField.setTextValidator(textValidatorMapper.toEntity(sectionFieldDto.getTextValidator()));
                    }
                    case BOOLEAN -> {
                        defaultValue = sectionFieldDto.getContentBoolean().getValue().toString();
                        //todo
                    }
                    case BREAK_LINE -> {
                        defaultValue = "BREAK_LINE";
                    }
                }
                sectionField.setDefaultValue(defaultValue);

                sectionFields.add(sectionField);
            }
            section.setSectionFields(sectionFields);
            sections.add(section);
        }
        template.setSections(sections);

        return template;
    }
}

