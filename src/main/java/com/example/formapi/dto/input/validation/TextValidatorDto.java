package com.example.formapi.dto.input.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("isRequired")
    private boolean isRequired;

    private Long minSize;
    private Long maxSize;

    @JsonProperty("isEmail")
    private boolean isEmail;

    @JsonProperty("isNoSpace")
    private boolean isNoSpace;

    @JsonProperty("isNoNumber")
    private boolean isNoNumber;

    private String regex;
}
