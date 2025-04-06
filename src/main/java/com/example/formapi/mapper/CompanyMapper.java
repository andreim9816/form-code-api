package com.example.formapi.mapper;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.User;
import com.example.formapi.dto.CompanyDto;
import com.example.formapi.dto.UserAdminDto;
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
        dto.setAdminUsers(company.getAdminUsers().stream().map(this::toDto).toList());
        return dto;
    }

    public UserAdminDto toDto(User user) {
        return UserAdminDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isAdmin(user.getIsAdmin())
                .companyIds(user.getCompanies().stream().map(Company::getId).toList())
                .companyRoles(user.getCompanyRoles().stream().map(companyRoleMapper::toDto).toList())
                .build();
    }
}
