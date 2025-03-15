package com.example.formapi.dto;

import lombok.Data;

@Data
public class FormSectionFieldDto {
    private Long id;
    private Long contentStringId;
    private Long contentBooleanId;
    private Long contentDateId;
    private Long contentNumberId;
    private Long sectionFieldId;
    private Long formSectionId;
}
