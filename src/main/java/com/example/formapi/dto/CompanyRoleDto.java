package com.example.formapi.dto;

import lombok.Data;

@Data
public class CompanyRoleDto {
    private Long id;
    private String name;
    private Long companyId;
}
