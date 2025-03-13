package com.example.formapi.mapper;

import com.example.formapi.domain.application.validation.TextValidator;
import com.example.formapi.dto.input.validation.TextValidatorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextValidatorMapper {
    public TextValidator toEntity(TextValidatorDto dto) {
        return TextValidator.builder()
                .id(dto.getId())
                .isRequired(dto.isRequired())
                .minSize(dto.getMinSize())
                .maxSize(dto.getMaxSize())
                .isEmail(dto.isEmail())
                .isNoSpace(dto.isNoSpace())
                .isNoNumber(dto.isNoNumber())
                .regex(dto.getRegex())
                .build();
    }

    public TextValidatorDto toDto(TextValidator entity) {
        return TextValidatorDto.builder()
                .id(entity.getId())
                .isRequired(entity.isRequired())
                .minSize(entity.getMinSize())
                .maxSize(entity.getMaxSize())
                .isEmail(entity.isEmail())
                .isNoSpace(entity.isNoSpace())
                .isNoNumber(entity.isNoNumber())
                .regex(entity.getRegex())
                .build();
    }
}
