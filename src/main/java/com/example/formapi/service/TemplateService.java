package com.example.formapi.service;

import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.repository.application.TemplateRepository;
import com.example.formapi.security.WebSecuritySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final WebSecuritySupport webSecuritySupport;
    private final TemplateMapper templateMapper;

    public Template findById(Long id) {
        Optional<Template> templateOpt = templateRepository.findById(id);
        if (templateOpt.isEmpty()) {
            throw new RuntimeException("Template not found");
        }
        Template template = templateOpt.get();
        sortFields(template);
        return template;
    }

    public Template createTemplate(ReqTemplateDto dto) {
        var template = templateMapper.toEntity(dto);
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