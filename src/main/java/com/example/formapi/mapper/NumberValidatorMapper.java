package com.example.formapi.mapper;

import com.example.formapi.domain.application.validation.NumberValidator;
import com.example.formapi.dto.input.validation.NumberValidatorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NumberValidatorMapper {
    public NumberValidator toEntity(NumberValidatorDto dto) {
        return NumberValidator.builder()
                .id(dto.getId())
                .isRequired(dto.isRequired())
                .minValue(dto.getMinValue())
                .maxValue(dto.getMaxValue())
                .build();
    }

    public NumberValidatorDto toDto(NumberValidator entity) {
        if (entity == null) {
            return null;
        }
        return NumberValidatorDto.builder()
                .id(entity.getId())
                .isRequired(entity.isRequired())
                .minValue(entity.getMinValue())
                .maxValue(entity.getMaxValue())
                .build();
    }
}
