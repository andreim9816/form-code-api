package com.example.formapi.mapper;

import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.dto.CompanyRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyRoleMapper {

    public CompanyRoleDto toDto(CompanyRole companyRole) {
        CompanyRoleDto dto = new CompanyRoleDto();
        dto.setId(companyRole.getId());
        dto.setName(companyRole.getName());
        dto.setCreateTemplate(companyRole.isCreateTemplate());
        dto.setValidateForm(companyRole.isValidateForm());
        dto.setCompanyId(companyRole.getCompany().getId());
        dto.setCompanyName(companyRole.getCompany().getName());
        return dto;
    }
}
