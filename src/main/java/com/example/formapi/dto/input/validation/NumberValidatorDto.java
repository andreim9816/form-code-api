package com.example.formapi.dto.input.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberValidatorDto {
    private Long id;
    private boolean isRequired;
    private Long minValue;
    private Long maxValue;
}
