package com.example.formapi.dto;

import com.example.formapi.domain.application.ContentType;
import com.example.formapi.dto.input.validation.DateValidatorDto;
import com.example.formapi.dto.input.validation.NumberValidatorDto;
import com.example.formapi.dto.input.validation.TextValidatorDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SectionFieldDto {
    private Long id;
    private Date addedDate;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private ContentType contentType;
    private String defaultValue;
    private Long sectionId;

    // validators
    private TextValidatorDto textValidator;
    private NumberValidatorDto numberValidator;
    private DateValidatorDto dateValidator;
}
