package com.example.formapi.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class ReqTemplateDto {
    private Long companyId;
    private String title;
    private String description;
    private List<ReqSectionDto> sections;
}
