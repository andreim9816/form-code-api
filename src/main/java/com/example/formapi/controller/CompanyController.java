package com.example.formapi.controller;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.Template;
import com.example.formapi.domain.application.User;
import com.example.formapi.dto.CompanyDto;
import com.example.formapi.dto.CompanyRoleDto;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.dto.input.ReqCompanyDto;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.mapper.CompanyMapper;
import com.example.formapi.mapper.CompanyRoleMapper;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.repository.application.CompanyRepository;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.security.WebSecuritySupport;
import com.example.formapi.service.CompanyService;
import com.example.formapi.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    private final WebSecuritySupport webSecuritySupport;
    private final CompanyRoleRepository companyRoleRepository;
    private final CompanyRepository companyRepository;
    private final TemplateService templateService;
    private final TemplateMapper templateMapper;
    private final CompanyRoleMapper companyRoleMapper;
    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getCompanies(@RequestParam(name = "createTemplate", required = false) boolean createTemplate) {
        Stream<Company> streamCompanies = companyService.findAll().stream();

        if (createTemplate) {
            User currentUser = webSecuritySupport.getUser();
            streamCompanies = streamCompanies.filter(
                    company -> company.getCompanyRoles().stream()
                            .anyMatch(role -> role.isCreateTemplate()
                                    && role.getUsers().stream().map(User::getId).toList().contains(currentUser.getId())
                            )
            );
        }
        return streamCompanies.map(companyMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{companyId}/roles")
    public List<CompanyRoleDto> getRolesForCompanyId(@PathVariable("companyId") Long companyId) {
        return companyRoleRepository.getCompanyRoleByCompany_Id(companyId).stream()
                .map(companyRoleMapper::toDto)
                .toList();
    }

    @GetMapping("/{companyId}/templates")
    public List<TemplateDto> getTemplatesForCompanyId(@PathVariable("companyId") Long companyId) {
        return templateService.findAll().stream()
                .filter(template -> Objects.equals(template.getCompany().getId(), companyId))
                .map(templateMapper::toDto)
                .toList();
    }

    @PostMapping("/{companyId}/templates")
    public TemplateDto createTemplate(@PathVariable("companyId") Long companyId, @RequestBody ReqTemplateDto dto) {
        Template template = templateService.createTemplate(companyId, dto);
        return templateMapper.toDto(template);
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody @Validated ReqCompanyDto dto) {
        return companyMapper.toDto(companyService.createCompany(dto));
    }

    @PatchMapping("/{companyId}")
    public CompanyDto updateCompany(@PathVariable("companyId") Long companyId, @RequestBody ReqCompanyDto dto) {
        return companyMapper.toDto(companyService.updateCompany(companyId, dto));
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable("companyId") Long companyId) {
//        for (long i = companyId; i <= 100; i++) {
        companyRepository.findById(companyId).ifPresent(company -> {

            for (CompanyRole role : company.getCompanyRoles()) {
                for (User user : role.getUsers()) {
                    user.getCompanyRoles().remove(role);
                }
                role.getUsers().clear(); // just in case
            }

            companyRepository.delete(company);
        });
//        }
    }
}
