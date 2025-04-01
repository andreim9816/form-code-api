package com.example.formapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private List<CompanyRoleDto> companyRoles;
}
