package com.example.formapi.service;

import com.example.formapi.domain.application.*;
import com.example.formapi.repository.application.FormRepository;
import com.example.formapi.security.WebSecuritySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.formapi.domain.enumeration.FormSectionStatus.IS_VALIDATION_SECTION;
import static com.example.formapi.domain.enumeration.FormSectionStatus.PENDING_VALIDATION;

@RequiredArgsConstructor
@Service
public class FormService {
    private final FormRepository formRepository;
    private final WebSecuritySupport webSecuritySupport;
    private final UserService userService;

    public Form createForm(Template template) {
        Form form = new Form();
        form.setCreatedDate(new Date());
        form.setTemplate(template);
        form.setCreatedUser(webSecuritySupport.getUser());
        //todo delete
        if (form.getCreatedUser() == null) {
            form.setCreatedUser(userService.loadUserByUsername("user3"));
        }
        for (Section section : template.getSections()) {
            FormSection formSection = createFormSectionFromSection(form, section);
            form.getFormSections().add(formSection);
        }
        // first section to complete is the first Section
        form.setCurrentSection(form.getFormSections().getFirst());
        // the first Section with the boolean isValidation=true
        form.setCurrentValidationSection(getFirstValidationSection(form));

        return form;
    }

    public FormSection createFormSectionFromSection(Form form, Section section) {
        FormSection formSection = new FormSection();
        formSection.setStatus(section.isValidation() ? IS_VALIDATION_SECTION : PENDING_VALIDATION);
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

    private FormSection getFirstValidationSection(Form form) {
        for (FormSection formSection : form.getFormSections()) {
            if (formSection.getSection().isValidation()) {
                return formSection;
            }
        }
        throw new RuntimeException(String.format("Could not determine the next validation section for form: %s", form));
    }
}
