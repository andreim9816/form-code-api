package com.example.formapi.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ReqCompanyDto {

    @NotBlank
    private String name;
    private List<String> companyRoles;
}
