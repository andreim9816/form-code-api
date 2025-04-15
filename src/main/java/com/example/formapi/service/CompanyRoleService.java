package com.example.formapi.service;

import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.CompanyRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyRoleService {
    private final CompanyRoleRepository repository;

    public List<CompanyRole> saveAll(List<CompanyRole> companyRoles) {
        return repository.saveAll(companyRoles);
    }

    public CompanyRole save(CompanyRole companyRole) {
        return repository.save(companyRole);
    }

    public CompanyRole findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidEntityException("Invalid company role id"));
    }
}