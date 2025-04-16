package com.example.formapi.dto.input;

import com.example.formapi.domain.enumeration.ContentType;
import com.example.formapi.domain.enumeration.PersonalDataType;
import com.example.formapi.dto.input.validation.DateValidatorDto;
import com.example.formapi.dto.input.validation.NumberValidatorDto;
import com.example.formapi.dto.input.validation.TextValidatorDto;
import lombok.Data;

@Data
public class ReqSectionFieldDto {
    private String id; // generated from frontend
    private ContentType contentType;
    private String defaultValue;
    private PersonalDataType personalDataType;
    // validation
    private TextValidatorDto textValidator;
    private NumberValidatorDto numberValidator;
    private DateValidatorDto dateValidator;
}
