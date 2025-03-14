package com.example.formapi.controller;

import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    private final TemplateService templateService;
    private final TemplateMapper templateMapper;

    @PostMapping("/{companyId}/templates")
    public TemplateDto createTemplate(@PathVariable("companyId") Long companyId, @RequestBody ReqTemplateDto dto) {
        Template template = templateService.createTemplate(companyId, dto);
        return templateMapper.toDto(template);
    }
}
