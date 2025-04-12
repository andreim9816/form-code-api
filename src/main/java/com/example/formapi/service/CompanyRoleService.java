package com.example.formapi.service;

import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.repository.application.SectionRepository;
import com.example.formapi.repository.application.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyRoleService {
    private final CompanyRoleRepository repository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    public List<CompanyRole> saveAll(List<CompanyRole> companyRoles) {
        return repository.saveAll(companyRoles);
    }

    public CompanyRole save(CompanyRole companyRole) {
        return repository.save(companyRole);
    }

    public CompanyRole findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidEntityException("Invalid company role id"));
    }

    public void delete(CompanyRole companyRole) {
        companyRole.getUsers().forEach(x -> x.removeCompanyRole(companyRole));
        companyRole.getSections().forEach(x -> x.removeCompanyRole(companyRole));
        repository.delete(companyRole);
    }
}