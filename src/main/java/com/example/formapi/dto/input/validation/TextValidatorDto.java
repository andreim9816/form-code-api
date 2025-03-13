package com.example.formapi.dto.input.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextValidatorDto {
    private Long id;
    private boolean isRequired;
    private Long minSize;
    private Long maxSize;
    private boolean isEmail;
    private boolean isNoSpace;
    private boolean isNoNumber;
    private String regex;
}
