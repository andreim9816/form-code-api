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
@RequestMapping(value = "/templates")
public class TemplateController {

    private final TemplateService service;
    private final TemplateMapper templateMapper;

    @GetMapping("/{templateId}")
    public TemplateDto getTemplate(@PathVariable("templateId") Long templateId) {
        Template template = service.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));
        return templateMapper.toDto(template);
    }

    @PostMapping
    public TemplateDto createTemplate(@RequestBody ReqTemplateDto dto) {
       Template template = service.createTemplate(dto);
       return templateMapper.toDto(template);
    }
}
