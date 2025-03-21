package com.example.formapi.dto.input;

import com.example.formapi.domain.application.Section;
import com.example.formapi.domain.enumeration.FormSectionStatus;
import com.example.formapi.dto.FormSectionFieldDto;
import lombok.Data;

import java.util.List;

@Data
public class FormSectionDto {
    private Long id;
    private FormSectionStatus status;
    private Section section;
    private List<FormSectionFieldDto> formSectionFields;
}
