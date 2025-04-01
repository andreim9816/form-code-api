package com.example.formapi.controller;

import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.CompanyDto;
import com.example.formapi.dto.CompanyRoleDto;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.dto.input.ReqCompanyDto;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.mapper.CompanyMapper;
import com.example.formapi.mapper.CompanyRoleMapper;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.service.CompanyService;
import com.example.formapi.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    private final CompanyRoleRepository companyRoleRepository;
    private final TemplateService templateService;
    private final TemplateMapper templateMapper;
    private final CompanyRoleMapper companyRoleMapper;
    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    @GetMapping("/{companyId}/roles")
    public List<CompanyRoleDto> getRolesForCompanyId(@PathVariable("companyId") Long companyId) {
        return companyRoleRepository.getCompanyRoleByCompany_Id(companyId).stream()
                .map(companyRoleMapper::toDto)
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
}
