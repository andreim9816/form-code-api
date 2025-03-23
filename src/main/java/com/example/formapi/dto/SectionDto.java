package com.example.formapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SectionDto {
    private Long id;
    private String title;
    @JsonProperty("isValidation")
    private boolean isValidation;
    private Long templateId;
    private List<CompanyRoleDto> companyRoles;
    private List<SectionFieldDto> sectionFields;
}
