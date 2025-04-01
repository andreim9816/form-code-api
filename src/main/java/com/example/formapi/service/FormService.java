package com.example.formapi.service;

import com.example.formapi.domain.application.*;
import com.example.formapi.exception.CustomException;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.FormRepository;
import com.example.formapi.repository.application.UserRepository;
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
    private final UserRepository userRepository;
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
        form.setCreatorUser(webSecuritySupport.getUser());

        for (Section section : template.getSections()) {
            FormSection formSection = createFormSectionFromSection(form, section);
            form.getFormSections().add(formSection);
        }

        // the first Section with the boolean isValidation=true
        form.setCurrentValidationSection(getFirstValidationSection(form));
        // first section to complete is the first Section
        form.setCurrentSection(form.getFormSections().getFirst());

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

    public User getNextUser(Form form, FormSection currentFormSection) {
        if (currentUserWasSimpleUser(form)) {
            List<User> complianceUsers = userRepository.findUsersByCompanyRoles(
                    currentFormSection.getSection().getCompanyRoles().stream().map(CompanyRole::getId).toList()
            );
            if (complianceUsers.isEmpty()) {
                throw new CustomException("No compliance users found");
            }
            //todo
            return complianceUsers.get(0);
        } else {
            // if validation is the same as current section, then the current user was ANAF and the next one should be the user
            return form.getCreatorUser();
        }
    }

    private FormSection getFirstValidationSection(Form form) {
        for (FormSection formSection : form.getFormSections()) {
            if (formSection.getSection().isValidation()) {
                return formSection;
            }
        }
        throw new CustomException("Couldn't find the next validation section");
    }

    public boolean currentUserWasSimpleUser(Form form) {
        Long currentSectionId = form.getCurrentSection().getId();
        Long currentValidationSectionId = form.getCurrentValidationSection().getId();

        return currentSectionId < currentValidationSectionId;
    }
}
