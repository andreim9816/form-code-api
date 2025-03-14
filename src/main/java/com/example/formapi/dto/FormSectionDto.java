package com.example.formapi.dto;

import com.example.formapi.domain.enumeration.FormSectionStatus;
import lombok.Data;

import java.util.List;

@Data
public class FormSectionDto {
    private Long id;
    private FormSectionStatus status;
    private List<FormSectionFieldDto> formSectionFields;

}
