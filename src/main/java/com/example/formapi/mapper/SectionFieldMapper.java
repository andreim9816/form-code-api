package com.example.formapi.mapper;

import com.example.formapi.domain.application.SectionField;
import com.example.formapi.dto.SectionFieldDto;
import com.example.formapi.dto.input.ReqSectionFieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectionFieldMapper {

    private final TextValidatorMapper textValidatorMapper;
    private final DateValidatorMapper dateValidatorMapper;
    private final NumberValidatorMapper numberValidatorMapper;

    public SectionField toEntity(ReqSectionFieldDto dto) {
        var sectionField = new SectionField();
        sectionField.setContentType(dto.getContentType());
        sectionField.setDefaultValue(dto.getDefaultValue());
        setValidator(sectionField, dto);
        return sectionField;
    }

    public SectionFieldDto toDto(SectionField sectionField) {
        SectionFieldDto dto = new SectionFieldDto();
        dto.setId(sectionField.getId());
//        dto.setAddedDate(sectionField.getAddedDate());
        dto.setDefaultValue(sectionField.getDefaultValue());
        dto.setContentType(sectionField.getContentType());
        dto.setSectionId(sectionField.getSection().getId());

        //validators
        switch (sectionField.getContentType()) {
            case STRING -> dto.setTextValidator(textValidatorMapper.toDto(sectionField.getTextValidator()));
            case NUMBER -> dto.setNumberValidator(numberValidatorMapper.toDto(sectionField.getNumberValidator()));
            case DATE -> dto.setDateValidator(dateValidatorMapper.toDto(sectionField.getDateValidator()));
        }
        return dto;
    }

    private void setValidator(SectionField sectionField, ReqSectionFieldDto sectionFieldDto) {
        switch (sectionFieldDto.getContentType()) {
            case DATE -> {
                sectionField.setDateValidator(dateValidatorMapper.toEntity(sectionFieldDto.getDateValidator()));
            }
            case NUMBER -> {
                sectionField.setNumberValidator(numberValidatorMapper.toEntity(sectionFieldDto.getNumberValidator()));
            }
            case STRING -> {
                sectionField.setTextValidator(textValidatorMapper.toEntity(sectionFieldDto.getTextValidator()));
            }
            case BOOLEAN -> {
                //todo validator
            }
        }
    }
}
