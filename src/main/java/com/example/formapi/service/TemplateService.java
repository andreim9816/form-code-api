package com.example.formapi.service;

import com.example.formapi.domain.application.Template;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.repository.application.TemplateRepository;
import com.example.formapi.security.WebSecuritySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final WebSecuritySupport webSecuritySupport;
    private final Mapper mapper;

    public Template createTemplate(ReqTemplateDto dto) {
        var template = mapper.toEntity(dto);
        template.setCreatorUser(webSecuritySupport.getUser());

        return templateRepository.save(template);
    }
}
