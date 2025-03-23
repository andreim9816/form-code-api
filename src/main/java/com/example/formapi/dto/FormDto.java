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
    //    private Long templateId;
    private Long currentUserId;
    private Long creatorUserId;
    private List<FormSectionDto> formSections;
}
