package com.example.formapi.service;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.dto.input.ReqCompanyDto;
import com.example.formapi.mapper.CompanyMapper;
import com.example.formapi.repository.application.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company createCompany(ReqCompanyDto dto) {
        Company company = new Company();
        company.setName(dto.getName());

        List<CompanyRole> companyRoles = new ArrayList<>();
        for (String role : dto.getCompanyRoles()) {
            CompanyRole companyRole = new CompanyRole();
            companyRole.setName(role);
            companyRoles.add(companyRole);
        }
        company.setCompanyRoles(companyRoles);
        // todo parse dto.getEmails() and send a creation link for update. Here maybe I can mock some data
        // maybe a user should create its role in the normal flow and then, the ADMIN will add him to the adminUsers list of the Company entity

        return companyRepository.save(company);
    }
}
