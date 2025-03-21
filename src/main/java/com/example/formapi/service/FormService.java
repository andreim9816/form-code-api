package com.example.formapi.service;

import com.example.formapi.domain.application.*;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.FormRepository;
import com.example.formapi.security.WebSecuritySupport;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void delete(Form form) {
        form.getFormSections().stream()
                .map(FormSection::getFormSectionFields)
                .flatMap(List::stream)
                .forEach(formSectionFieldService::deleteContentById);
        formRepository.delete(form);
    }

    @Transactional
    public Form createForm(Template template) {
        Form form = new Form();
        form.setCreatedDate(LocalDate.now());
        form.setTemplate(template);
        form.setCurrentUser(webSecuritySupport.getUser());
        //todo delete
        if (form.getCurrentUser() == null) {
            form.setCurrentUser(userService.loadUserByUsername("user3"));
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

    public User getNextUser(Form form) {
        if (form.getCurrentSection().getId().equals(form.getCurrentValidationSection().getId())) {
            // if validation is the same as current section, then the current user was ANAF and the next one should be the user
            return form.getCurrentUser();
        } else {

        }
        return null;
    }

    private FormSection getFirstValidationSection(Form form) {
        for (FormSection formSection : form.getFormSections()) {
            if (formSection.getSection().isValidation()) {
                return formSection;
            }
        }
        //todo add this validation at template creation!!
        throw new RuntimeException("Please add at least one validation section");
    }
}
