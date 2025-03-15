package com.example.formapi.service;

import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.repository.application.TemplateRepository;
import com.example.formapi.security.WebSecuritySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final WebSecuritySupport webSecuritySupport;
    private final TemplateMapper templateMapper;

    public Template findById(Long id) {
        Template template = templateRepository.findById(id).orElseThrow(() -> new InvalidEntityException(id));
        sortFields(template);
        return template;
    }

    public Template createTemplate(Long companyId, ReqTemplateDto dto) {
        var template = templateMapper.toEntity(companyId, dto);
        template.setCreatorUser(webSecuritySupport.getUser());

        return templateRepository.save(template);
    }

    private static void sortFields(Template template) {
        Collections.sort(template.getSections());
        template.getSections().forEach(
                section -> Collections.sort(section.getSectionFields())
        );
    }
}