package com.example.formapi.service;

import com.example.formapi.domain.application.Section;
import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.mapper.TemplateMapper;
import com.example.formapi.repository.application.TemplateRepository;
import com.example.formapi.security.WebSecuritySupport;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final FormSectionFieldService formSectionFieldService;
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

    @Transactional
    public void deleteById(Long id) {
        Template template = findById(id);
        template.getSections().stream()
                .map(Section::getSectionFields)
                .flatMap(List::stream)
                .map(SectionField::getFormSectionFields)
                .flatMap(List::stream)
                .forEach(formSectionFieldService::deleteContentById);
        templateRepository.deleteById(id);
    }

    private static void sortFields(Template template) {
        Collections.sort(template.getSections());
        template.getSections().forEach(
                section -> Collections.sort(section.getSectionFields())
        );
    }
}