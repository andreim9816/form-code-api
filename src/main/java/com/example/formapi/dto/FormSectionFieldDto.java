package com.example.formapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormSectionFieldDto {
    private Long id;
    @JsonProperty("isBreakLine")
    private boolean isBreakLine;
    private Long contentStringId;
    private Long contentBooleanId;
    private Long contentDateId;
    private Long contentNumberId;
    private Long sectionFieldId;
    private Long formSectionId;
}
