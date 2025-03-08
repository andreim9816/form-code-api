package com.example.formapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectionDto {
    private Long id;
    private String title;
    private Boolean autoValidated;
    private Long previousSectionId;
    private Long templateId;
    private List<SectionFieldDto> sectionFields;
}
