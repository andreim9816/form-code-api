package com.example.formapi.dto.input;

import com.example.formapi.domain.application.ContentType;
import com.example.formapi.domain.client.ContentBoolean;
import com.example.formapi.domain.client.ContentDate;
import com.example.formapi.domain.client.ContentNumber;
import com.example.formapi.domain.client.ContentString;
import com.example.formapi.dto.input.validation.DateValidatorDto;
import com.example.formapi.dto.input.validation.NumberValidatorDto;
import com.example.formapi.dto.input.validation.TextValidatorDto;
import lombok.Data;

@Data
public class ReqSectionFieldDto {
    private String id; // generated from frontend
    private ContentType contentType;
    private ContentNumber contentNumber;
    private ContentString contentString;
    private ContentBoolean contentBoolean;
    private ContentDate contentDate;

    // validation
    private TextValidatorDto textValidator;
    private NumberValidatorDto numberValidator;
    private DateValidatorDto dateValidator;
}
