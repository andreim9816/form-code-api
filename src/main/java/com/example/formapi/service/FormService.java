package com.example.formapi.service;

import com.example.formapi.domain.application.*;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.FormRepository;
import com.example.formapi.security.WebSecuritySupport;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.example.formapi.domain.enumeration.FormSectionStatus.IS_VALIDATION_SECTION;
import static com.example.formapi.domain.enumeration.FormSectionStatus.PENDING_VALIDATION;

@RequiredArgsConstructor
@Service
public class FormService {
    private final UserService userService;
    private final FormSectionFieldService formSectionFieldService;
    private final FormRepository formRepository;
    private final WebSecuritySupport webSecuritySupport;

    public List<Form> findALl() {
        return formRepository.findAll();
    }

    public Form findById(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new InvalidEntityException(id));
    }

    public void deleteById(Long id) {
        formRepository.deleteById(id);
    }

    @Transactional
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

        return formRepository.save(form);
    }

    public FormSection createFormSectionFromSection(Form form, Section section) {
        FormSection formSection = new FormSection();
        formSection.setStatus(section.isValidation() ? IS_VALIDATION_SECTION : PENDING_VALIDATION);
        formSection.setSection(section);
        formSection.setForm(form);
        for (SectionField sectionField : section.getSectionFields()) {
            FormSectionField formSectionField = formSectionFieldService.createFormSectionField(formSection, sectionField);
            formSection.getFormSectionFields().add(formSectionField);
        }
        return formSection;
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
