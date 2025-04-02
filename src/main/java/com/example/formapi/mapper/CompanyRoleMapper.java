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
        dto.setCompanyId(companyRole.getCompany().getId());
        return dto;
    }
}
