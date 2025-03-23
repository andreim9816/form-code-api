package com.example.formapi.service;

import com.example.formapi.domain.application.Form;
import com.example.formapi.domain.application.FormSection;
import com.example.formapi.dto.input.FormSectionDto;
import com.example.formapi.repository.application.FormSectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FormSectionService {

    private final FormSectionFieldService formSectionFieldService;
    private final FormService formService;
    private final FormSectionRepository formSectionRepository;

    public FormSection findById(Long id) {
        return formSectionRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<FormSection> update(List<FormSectionDto> formSections) {
        List<FormSection> updatedFormSections = formSections.stream()
                .map(this::update)
                .collect(toList());

        updateNextSectionAndUser(updatedFormSections.getFirst().getForm());

        return updatedFormSections;
    }

    public FormSection update(FormSectionDto formSectionDto) {
        FormSection formSection = findById(formSectionDto.getId());
        formSectionDto.getFormSectionFields().stream()
                .filter(formSectionFieldDto -> formSectionFieldDto.getSectionField().getDefaultValue() == null)
                .forEach(formSectionFieldService::updateFormSectionField);

        return formSection;
    }

    public void updateNextSectionAndUser(Form form) {
        if (formService.currentUserWasSimpleUser(form)) {
            // current user is a simple one, so the next must be a COMPLIANCE ONE
            form.setCurrentUser(formService.getNextUser(form));
            form.setCurrentSection(form.getCurrentValidationSection());
            //validation stays the same
        } else {
            // current user was a compliance one, so we need to get to the next validation sections

            // we need to check if the form was not completed yet
            if (form.getCurrentValidationSection().getId().equals(form.getFormSections().getLast().getId())) {
                // the form is finished
                form.setCurrentUser(null);
                form.setCurrentValidationSection(null);
                form.setCurrentSection(null);
            } else {
                // else go to the next section after the validation one
                form.setCurrentUser(formService.getNextUser(form));
                FormSection nextFormSection = form.getFormSections().stream()
                        .filter(formSection -> formSection.getId() > form.getCurrentValidationSection().getId())
                        .findFirst().get();

                FormSection nextValidationFormSection = form.getFormSections().stream()
                        .filter(formSection -> formSection.getId() > nextFormSection.getId())
                        .findFirst().get();

                form.setCurrentSection(nextFormSection);
                form.setCurrentValidationSection(nextValidationFormSection);
            }
        }
    }
}
