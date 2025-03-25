package com.example.formapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FormDto {
    private Long id;
    private LocalDate createdDate;
    private LocalDate finishedDate;
    private Long currentValidationSectionId;
    private Long currentSectionId;
    private Long currentUserId;
    private Long creatorUserId;
    private TemplateDto template;
    private List<FormSectionDto> formSections;
}
