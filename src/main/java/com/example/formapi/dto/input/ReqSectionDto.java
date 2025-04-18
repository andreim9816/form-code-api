package com.example.formapi.dto.input;

import com.example.formapi.dto.CompanyRoleDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReqSectionDto {
    private String title;
    @JsonProperty("isValidation")
    private boolean isValidation;
    private List<CompanyRoleDto> companyRoles = new ArrayList<>();
    private List<ReqSectionFieldDto> sectionFields = new ArrayList<>();
}
