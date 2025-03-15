package com.example.formapi.service;

import com.example.formapi.domain.application.FormSection;
import com.example.formapi.domain.application.FormSectionField;
import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.client.ContentDate;
import com.example.formapi.domain.client.ContentNumber;
import com.example.formapi.domain.client.ContentString;
import com.example.formapi.repository.client.ContentDateRepository;
import com.example.formapi.repository.client.ContentNumberRepository;
import com.example.formapi.repository.client.ContentStringRepository;
import com.example.formapi.repository.application.FormSectionFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormSectionFieldService {

    private final FormSectionFieldRepository formSectionFieldRepository;
    private final ContentStringRepository contentStringRepository;
    private final ContentNumberRepository contentNumberRepository;
    private final ContentDateRepository contentDateRepository;

    public FormSectionField createFormSectionField(FormSection formSection, SectionField sectionField) {
        FormSectionField formSectionField = new FormSectionField();
        formSectionField.setFormSection(formSection);
        formSectionField.setSectionField(sectionField);
        // save to persist and obtain id
        formSectionField = formSectionFieldRepository.save(formSectionField);

        switch (sectionField.getContentType()) {
            case STRING -> {
                ContentString content = new ContentString();
                content.setFormSectionFieldId(formSectionField.getId());
                contentStringRepository.save(content);
            }
            case NUMBER -> {
                ContentNumber content = new ContentNumber();
                content.setFormSectionFieldId(formSectionField.getId());
                contentNumberRepository.save(content);
            }
            case DATE -> {
                ContentDate content = new ContentDate();
                content.setFormSectionFieldId(formSectionField.getId());
                contentDateRepository.save(content);
            }
        }

        return formSectionField;
    }
}
