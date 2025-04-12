package com.example.formapi.dto.input.validation;

import lombok.Data;

@Data
public class ReqCompanyRole {
    private Long companyRoleId;
    private String name;
    private boolean createTemplate;
}
