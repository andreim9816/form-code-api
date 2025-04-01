package com.example.formapi.mapper;

import com.example.formapi.domain.application.Company;
import com.example.formapi.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    private final CompanyRoleMapper companyRoleMapper;

    public CompanyDto toDto(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setCompanyRoles(company.getCompanyRoles().stream().map(companyRoleMapper::toDto).toList());
        return dto;
    }
}
