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
    private UserDto user;
    private List<FormSectionDto> formSections;
}
