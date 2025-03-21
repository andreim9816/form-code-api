package com.example.formapi.service;

import com.example.formapi.domain.application.FormSection;
import com.example.formapi.dto.FormSectionFieldDto;
import com.example.formapi.dto.input.FormSectionDto;
import com.example.formapi.repository.application.FormSectionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

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
        return updatedFormSections;
    }

    public FormSection update(FormSectionDto formSectionDto) {
        FormSection formSection = findById(formSectionDto.getId());

        Long currentFormSectionId = formSection.getId();
        Long currentSectionId = formSection.getForm().getCurrentSection().getId();
        Long currentValidationSectionId = formSection.getForm().getCurrentValidationSection().getId();

        //technically the frontend already filters them, but I'll do another filtering here to be sure

        Predicate<FormSectionFieldDto> isFieldCompletedByUser = dto -> dto.getSectionField().getDefaultValue() == null;

        if (currentSectionId <= currentFormSectionId && currentFormSectionId < currentValidationSectionId) {
            // filled in by simple user
            formSectionDto.getFormSectionFields().stream()
                    .filter(isFieldCompletedByUser)
                    .forEach(formSectionFieldService::updateFormSectionField);

        } else if (currentFormSectionId.equals(currentValidationSectionId)) {
            // filled in by ANAF user
            //todo add a mapping between section and UserRole
        }

        return formSection;
    }
}
