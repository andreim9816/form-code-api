package com.example.formapi.controller;

import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.CompanyRoleDto;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.mapper.CompanyRoleMapper;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    private final CompanyRoleRepository companyRoleRepository;
    private final TemplateService templateService;
    private final TemplateMapper templateMapper;
    private final CompanyRoleMapper companyRoleMapper;

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
}
