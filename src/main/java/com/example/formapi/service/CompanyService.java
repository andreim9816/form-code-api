package com.example.formapi.service;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.User;
import com.example.formapi.dto.input.ReqCompanyDto;
import com.example.formapi.dto.input.validation.ReqCompanyRole;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyRoleService companyRoleService;
    private final UserService userService;

    //todo
    public Long getCompanyIdForInstance() {
        return 1L;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long companyId) {
        return companyRepository.findById(companyId);
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

        List<User> adminUsers = new ArrayList<>();
        for (Long adminUserId : dto.getAdminUserIds()) {
            User adminUser = userService.findById(adminUserId);
            adminUsers.add(adminUser);
        }
        company.setAdminUsers(adminUsers);

        company.setCompanyRoles(companyRoles);
        // todo parse dto.getEmails() and send a creation link for update. Here maybe I can mock some data
        // maybe a user should create its role in the normal flow and then, the ADMIN will add him to the adminUsers list of the Company entity

        return companyRepository.save(company);
    }

    //todo warning!!!! don't delete company role from Company!!!!
    @Transactional
    public Company updateCompany(Long companyId, ReqCompanyDto dto) {
        Company company = findById(companyId).orElseThrow(() -> new InvalidEntityException("Invalid company"));
        company.setName(dto.getName());

        List<CompanyRole> newCompanyRoles = new ArrayList<>();
        List<CompanyRole> updatedCompanyRoles = new ArrayList<>();
        List<CompanyRole> deletedCompanyRoles = new ArrayList<>();

        dto.getCompanyRoles().stream()
                .filter(x -> x.getCompanyRoleId() == null)
                .forEach(companyRole -> {
                    CompanyRole newCompanyRole = new CompanyRole();
                    newCompanyRole.setName(companyRole.getName());
                    newCompanyRole.setCreateTemplate(companyRole.isCreateTemplate());
                    newCompanyRole.setCompany(company);
                    company.getCompanyRoles().add(newCompanyRole);
                });

        dto.getCompanyRoles().stream()
                .filter(x -> x.getCompanyRoleId() != null)
                .forEach(companyRole -> {
                    CompanyRole updatedCompanyRole = companyRoleService.findById(companyRole.getCompanyRoleId());
                    updatedCompanyRole.setName(companyRole.getName());
                    updatedCompanyRole.setCreateTemplate(companyRole.isCreateTemplate());
                    updatedCompanyRole.setCompany(company); //?
                });

        List<Long> editedCompanyIds = dto.getCompanyRoles().stream()
                .filter(x -> x.getCompanyRoleId() != null)
                .map(ReqCompanyRole::getCompanyRoleId)
                .collect(Collectors.toList());

        deletedCompanyRoles = company.getCompanyRoles().stream()
                .filter(companyRole -> companyRole.getId() != null)
                .filter(companyRole -> !editedCompanyIds.contains(companyRole.getId()))
                .collect(Collectors.toList());

        deletedCompanyRoles.forEach(companyRole -> {
            company.getCompanyRoles().remove(companyRole);
        });
        deletedCompanyRoles.forEach(companyRole -> companyRole.setCompany(null));

        List<Long> addedUsersIds = new ArrayList<>();
        List<User> deletedUsers = new ArrayList<>();
        List<Long> currentAdminIds = company.getAdminUsers().stream().map(User::getId).toList();

        addedUsersIds = dto.getAdminUserIds().stream()
                .filter(id -> !currentAdminIds.contains(id))
                .toList();
        deletedUsers = company.getAdminUsers().stream()
                .filter(user -> !dto.getAdminUserIds().contains(user.getId()))
                .toList();

        addedUsersIds.forEach(id -> {
            User user = userService.findById(id);
            company.getAdminUsers().add(user);
            user.getCompanies().add(company);
        });
        deletedUsers.forEach(user -> {
            user.getCompanies().remove(company);
            company.getAdminUsers().remove(user);
        });

        return companyRepository.save(company);
    }
}
