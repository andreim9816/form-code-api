package com.example.formapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDto {
    private Long id;
    private String title;
    private String description;
    private Long companyId;
    private Long creatorUserId;
    private List<SectionDto> sections;
}
