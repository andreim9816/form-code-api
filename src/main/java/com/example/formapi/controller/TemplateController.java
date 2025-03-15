package com.example.formapi.controller;

import com.example.formapi.domain.application.Form;
import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.FormDto;
import com.example.formapi.dto.TemplateDto;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.service.FormService;
import com.example.formapi.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/templates")
public class TemplateController {

    private final FormService formService;
    private final TemplateService service;
    private final TemplateMapper templateMapper;

    @GetMapping("/{templateId}")
    public TemplateDto getTemplate(@PathVariable("templateId") Long templateId) {
        Template template = service.findById(templateId);
        return templateMapper.toDto(template);
    }


    @PostMapping("/{templateId}")
    public FormDto createForm(@PathVariable("templateId") Long templateId) {
        Template template = service.findById(templateId);
        Form form = formService.createForm(template);
        return null;
    }
}
