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
import com.example.formapi.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormSectionFieldService {

    private final ContentStringRepository contentStringRepository;
    private final ContentNumberRepository contentNumberRepository;
    private final ContentDateRepository contentDateRepository;

    public FormSectionField createFormSectionField(FormSection formSection, SectionField sectionField) {
        FormSectionField formSectionField = new FormSectionField();
        formSectionField.setFormSection(formSection);
        formSectionField.setSectionField(sectionField);

        switch (sectionField.getContentType()) {
            case STRING -> {
                ContentString content = new ContentString();
                content.setValue(sectionField.getDefaultValue());
                content = contentStringRepository.save(content);
                formSectionField.setContentStringId(content.getId());
            }
            case NUMBER -> {
                ContentNumber content = new ContentNumber();
                content.setValue(Long.valueOf(sectionField.getDefaultValue()));
                content = contentNumberRepository.save(content);
                formSectionField.setContentNumberId(content.getId());
            }
            case DATE -> {
                ContentDate content = new ContentDate();
                content.setValue(DateUtils.parseDate(sectionField.getDefaultValue()));
                content = contentDateRepository.save(content);
                System.out.println(content);
                formSectionField.setContentDateId(content.getId());
            }
            case BREAK_LINE ->
                    formSectionField.setBreakLine(true); //todo won't this be redundant because SectionField has ContentType field???
        }
        return formSectionField;
    }

    public void deleteContentById(FormSectionField formSectionField) {
        switch (formSectionField.getSectionField().getContentType()) {
            case STRING -> deleteContentStringByIdIfExists(formSectionField.getContentStringId());
            case NUMBER -> deleteContentNumberByIdIfExists(formSectionField.getContentNumberId());
            case DATE -> deleteContentDateByIdIfExists(formSectionField.getContentDateId());
        }
    }

    public ContentNumber getContentNumberById(Long id) {
        return contentNumberRepository.findById(id).orElse(null);
    }

    public ContentString getContentStringById(Long id) {
        return contentStringRepository.findById(id).orElse(null);
    }

    public ContentDate getContentDateById(Long id) {
        return contentDateRepository.findById(id).orElse(null);
    }

    public void deleteContentStringByIdIfExists(Long id) {
        if (id != null) {
            contentStringRepository.deleteById(id);
        }
    }

    public void deleteContentNumberByIdIfExists(Long id) {
        if (id != null) {
            contentNumberRepository.deleteById(id);
        }
    }

    public void deleteContentDateByIdIfExists(Long id) {
        if (id != null) {
            contentDateRepository.deleteById(id);
        }
    }
}
