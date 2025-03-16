package com.example.formapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SectionLiteDto {
    private Long id;
    private String title;
    @JsonProperty("isValidation")
    private boolean isValidation;
}
