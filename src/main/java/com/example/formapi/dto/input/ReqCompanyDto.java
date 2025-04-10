package com.example.formapi.dto.input;

import com.example.formapi.dto.input.validation.ReqCompanyRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ReqCompanyDto {

    @NotBlank
    private String name;
    private List<ReqCompanyRole> companyRoles;
}
