package com.example.formapi.dto;

import lombok.Data;

@Data
public class CompanyRoleDto {
    private Long id;
    private String name;
    private boolean createTemplate;
    private boolean validateForm;
    private Long companyId;
    private String companyName;
}
