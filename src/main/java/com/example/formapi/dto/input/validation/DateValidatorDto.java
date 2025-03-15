package com.example.formapi.dto.input.validation;

import com.example.formapi.domain.application.validation.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateValidatorDto {
    private Long id;

    @JsonProperty("isRequired")
    private boolean isRequired;

    private LocalDate startDate;
    private LocalDate endDate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private DateTime dateTime;
}
