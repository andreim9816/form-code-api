package com.example.formapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FormDto {
    private Long id;
    private LocalDate createdDate;
    private LocalDate finishedDate;
    private LocalDate lastModifiedDate;
    private Long currentValidationSectionId;
    private Long currentSectionId;
    private UserDto currentUser;
    private UserDto creatorUser;
    private TemplateDto template;
    private List<FormSectionDto> formSections;
}
