package com.example.formapi.dto.input.validation;

import com.example.formapi.domain.application.validation.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateValidatorDto {
    private Long id;
    private boolean isRequired;
    private Date startDate;
    private Date endDate;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private DateTime dateTime;
}
