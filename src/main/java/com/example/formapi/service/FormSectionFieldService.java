package com.example.formapi.service;

import com.example.formapi.domain.application.FormSection;
import com.example.formapi.domain.application.FormSectionField;
import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.client.ContentDate;
import com.example.formapi.domain.client.ContentFile;
import com.example.formapi.domain.client.ContentNumber;
import com.example.formapi.domain.client.ContentString;
import com.example.formapi.dto.FormSectionFieldDto;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.repository.application.FormSectionFieldRepository;
import com.example.formapi.repository.client.ContentDateRepository;
import com.example.formapi.repository.client.ContentFileRepository;
import com.example.formapi.repository.client.ContentNumberRepository;
import com.example.formapi.repository.client.ContentStringRepository;
import com.example.formapi.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormSectionFieldService {

    private final FormSectionFieldRepository formSectionFieldRepository;
    private final ContentStringRepository contentStringRepository;
    private final ContentNumberRepository contentNumberRepository;
    private final ContentDateRepository contentDateRepository;
    private final ContentFileRepository contentFileRepository;

    public FormSectionField findById(Long id) {
        return formSectionFieldRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(id));
    }

    public FormSectionField createFormSectionField(FormSection formSection, SectionField sectionField) {
        FormSectionField formSectionField = new FormSectionField();
        formSectionField.setFormSection(formSection);
        formSectionField.setSectionField(sectionField);

        if (sectionField.getDefaultValue() == null) {
            switch (sectionField.getContentType()) {
                case STRING -> {
                    ContentString content = new ContentString();
                    content.setValue(sectionField.getDefaultValue());
                    content = contentStringRepository.save(content);
                    formSectionField.setContentStringId(content.getId());
                }
                case NUMBER -> {
                    ContentNumber content = new ContentNumber();
                    content.setValue(sectionField.getDefaultValue() == null ? null : Long.valueOf(sectionField.getDefaultValue()));
                    content = contentNumberRepository.save(content);
                    formSectionField.setContentNumberId(content.getId());
                }
                case DATE -> {
                    ContentDate content = new ContentDate();
                    content.setValue(sectionField.getDefaultValue() == null ? null : DateUtils.parseDate(sectionField.getDefaultValue()));
                    content = contentDateRepository.save(content);
                    formSectionField.setContentDateId(content.getId());
                }
                case FILE -> {
                    ContentFile content = new ContentFile();
                    content.setValue(null);
                    content = contentFileRepository.save(content);
                    formSectionField.setContentFileId(content.getId());
                }
                case BREAK_LINE ->
                        formSectionField.setBreakLine(true); //todo won't this be redundant because SectionField has ContentType field???
            }
        }
        return formSectionField;
    }

    public void updateFormSectionField(FormSectionFieldDto dto) {
        switch (dto.getSectionField().getContentType()) {
            case STRING -> {
                ContentString content = getContentStringById(dto.getContentString().getId());
                content.setValue(dto.getContentString().getValue());
                contentStringRepository.save(content);
            }
            case NUMBER -> {
                ContentNumber content = getContentNumberById(dto.getContentNumber().getId());
                content.setValue(dto.getContentNumber().getValue());
                contentNumberRepository.save(content);
            }
            case DATE -> {
                ContentDate content = getContentDateById(dto.getContentDate().getId());
                content.setValue(dto.getContentDate().getValue());
                contentDateRepository.save(content);
            }
//            case FILE -> {
//                ContentFile content = getContentFileById(dto.getContentFile().getId());
//                content.setName(dto.getContentFile().getName()); //todo
//                content.setContent(dto.getContentFile().getContent());
//            }
        }
    }

    public void deleteContentById(FormSectionField formSectionField) {
        switch (formSectionField.getSectionField().getContentType()) {
            case STRING -> deleteContentStringByIdIfExists(formSectionField.getContentStringId());
            case NUMBER -> deleteContentNumberByIdIfExists(formSectionField.getContentNumberId());
            case DATE -> deleteContentDateByIdIfExists(formSectionField.getContentDateId());
            case FILE -> deleteContentFileByIdIfExists(formSectionField.getContentFileId());
            case BREAK_LINE -> {
            }
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

    public ContentFile getContentFileById(Long id) {
        return contentFileRepository.findById(id).orElse(null);
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

    public void deleteContentFileByIdIfExists(Long id) {
        if (id != null) {
            contentFileRepository.deleteById(id);
        }
    }
}
