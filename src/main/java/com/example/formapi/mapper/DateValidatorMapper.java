package com.example.formapi.mapper;

import com.example.formapi.domain.application.validation.DateValidator;
import com.example.formapi.dto.input.validation.DateValidatorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DateValidatorMapper {
    public DateValidator toEntity(DateValidatorDto dto) {
        return DateValidator.builder()
                .id(dto.getId())
                .isRequired(dto.isRequired())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .dateTime(dto.getDateTime())
                .build();
    }

    public DateValidatorDto toDto(DateValidator entity) {
        if (entity == null) {
            return null;
        }
        return DateValidatorDto.builder()
                .id(entity.getId())
                .isRequired(entity.isRequired())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .dateTime(entity.getDateTime())
                .build();
    }
}
