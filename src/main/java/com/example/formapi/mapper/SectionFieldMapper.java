package com.example.formapi.mapper;

import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.application.validation.NumberValidator;
import com.example.formapi.domain.application.validation.TextValidator;
import com.example.formapi.dto.SectionFieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectionFieldMapper {

    private final TextValidatorMapper textValidatorMapper;
    private final DateValidatorMapper dateValidatorMapper;
    private final NumberValidatorMapper numberValidatorMapper;
//    public SectionField toEntity(ReqSectionFieldDto dto) {
//        return SectionField.builder()
//                .id(dto.getId())
//                .defaultValue(dto.)
//                .contentType(dto.getContentType())
//                .build();
//    }

    public SectionFieldDto toDto(SectionField sectionField) {
        SectionFieldDto dto = new SectionFieldDto();
        dto.setId(sectionField.getId());
//        dto.setAddedDate(sectionField.getAddedDate());
        dto.setDefaultValue(sectionField.getDefaultValue());
        dto.setContentType(sectionField.getContentType());
        dto.setSectionId(sectionField.getSection().getId());

        //validators
        dto.setTextValidator(textValidatorMapper.toDto(sectionField.getTextValidator()));
        dto.setTextValidator(dateValidatorMapper.toDto(sectionField.getDateValidator()));
        dto.setTextValidator(numberValidatorMapper.toDto(sectionField.getNumberValidator()));
        return dto;
    }
}
