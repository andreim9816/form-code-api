package com.example.formapi.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class ReqFormDto {
    private List<FormSectionDto> formSections;
}
