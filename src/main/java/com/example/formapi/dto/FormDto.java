package com.example.formapi.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FormDto {
    private Long id;
    private Date startedOn;
    private Date finishedOn;
    private Long currentValidationSectionId;
    private Long currentSectionId;
    private List<FormSectionDto> formSections;
}
