package com.example.formapi.controller;

import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/templates")
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public void createTemplate(@RequestBody ReqTemplateDto dto) {
        templateService.createTemplate(dto);
    }
}
