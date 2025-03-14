package com.example.formapi.service;

import com.example.formapi.domain.application.*;
import com.example.formapi.domain.enumeration.FormSectionStatus;
import com.example.formapi.repository.application.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final UserService userService;

    public Form createForm(Template template) {
        Form form = new Form();
        form.setCreatedDate(new Date());
        form.setTemplate(template);
        form.setCreatedUser(userService.loadUserByUsername("user3")); //todo
        for (Section section : template.getSections()) {
            FormSection formSection = createFormSectionFromSection(form, section);
            form.getFormSections().add(formSection);
        }
        //set currentValidationSection, currentCompleteSection

        return form;
    }

    public FormSection createFormSectionFromSection(Form form, Section section) {
        FormSection formSection = new FormSection();
        formSection.setStatus(FormSectionStatus.PENDING_VALIDATION);
        formSection.setSection(section);
        formSection.setForm(form);
        // set formSectionField
        for (SectionField sectionField : section.getSectionFields()) {
            FormSectionField formSectionField = createFormSectionField(formSection, sectionField);
            formSection.getFormSectionFields().add(formSectionField);
        }
        return formSection;
    }

    public FormSectionField createFormSectionField(FormSection formSection, SectionField sectionField) {
        FormSectionField formSectionField = new FormSectionField();
        formSectionField.setSectionField(sectionField);
        formSectionField.setFormSection(formSection);

        switch (sectionField.getContentType()) {
            case STRING -> {

            }
        }
        return formSectionField;
    }
}
