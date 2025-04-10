package com.example.formapi.service;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.dto.input.ReqCompanyDto;
import com.example.formapi.dto.input.validation.ReqCompanyRole;
import com.example.formapi.repository.application.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company createCompany(ReqCompanyDto dto) {
        Company company = new Company();
        company.setName(dto.getName());

        List<CompanyRole> companyRoles = new ArrayList<>();
        for (ReqCompanyRole role : dto.getCompanyRoles()) {
            CompanyRole companyRole = new CompanyRole();
            companyRole.setName(role.getName());
            companyRole.setCreateTemplate(role.isCreateTemplate());
            companyRoles.add(companyRole);

            companyRole.setCompany(company);
        }
        company.setCompanyRoles(companyRoles);
        // todo parse dto.getEmails() and send a creation link for update. Here maybe I can mock some data
        // maybe a user should create its role in the normal flow and then, the ADMIN will add him to the adminUsers list of the Company entity

        return companyRepository.save(company);
    }
}
