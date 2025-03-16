package com.example.formapi.mapper;

import com.example.formapi.domain.application.FormSectionField;
import com.example.formapi.domain.client.ContentDate;
import com.example.formapi.domain.client.ContentNumber;
import com.example.formapi.domain.client.ContentString;
import com.example.formapi.dto.FormSectionFieldDto;
import com.example.formapi.dto.content.ContentDateDto;
import com.example.formapi.dto.content.ContentNumberDto;
import com.example.formapi.dto.content.ContentStringDto;
import com.example.formapi.repository.client.ContentDateRepository;
import com.example.formapi.repository.client.ContentNumberRepository;
import com.example.formapi.repository.client.ContentStringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FormSectionFieldMapper {

    private final SectionFieldMapper sectionFieldMapper;
    private final ContentStringRepository contentStringRepository;
    private final ContentNumberRepository contentNumberRepository;
    private final ContentDateRepository contentDateRepository;

    public FormSectionFieldDto toDto(FormSectionField entity) {
        FormSectionFieldDto dto = new FormSectionFieldDto();
        dto.setId(entity.getId());
        dto.setSectionField(sectionFieldMapper.toDto(entity.getSectionField()));
        dto.setFormSectionId(entity.getFormSection().getId());
        dto.setBreakLine(entity.isBreakLine());

        dto.setContentBooleanId(entity.getContentBooleanId()); //todo
        dto.setContentString(toDto(contentStringRepository.findById(sanitize(entity.getContentStringId())).orElse(null)));
        dto.setContentDate(toDto(contentDateRepository.findById(sanitize(entity.getContentDateId())).orElse(null)));
        dto.setContentNumber(toDto(contentNumberRepository.findById(sanitize(entity.getContentNumberId())).orElse(null)));

        return dto;
    }

    public ContentStringDto toDto(ContentString entity) {
        if (entity != null) {
            ContentStringDto dto = new ContentStringDto();
            dto.setId(entity.getId());
            dto.setValue(entity.getValue());
            return dto;
        }
        return null;
    }

    public ContentNumberDto toDto(ContentNumber entity) {
        if (entity != null) {
            ContentNumberDto dto = new ContentNumberDto();
            dto.setId(entity.getId());
            dto.setValue(entity.getValue());
            return dto;
        }
        return null;
    }

    public ContentDateDto toDto(ContentDate entity) {
        if (entity != null) {
            ContentDateDto dto = new ContentDateDto();
            dto.setId(entity.getId());
            dto.setValue(entity.getValue());
            return dto;
        }
        return null;
    }

    public Long sanitize(Long id) {
        return id == null ? -1 : id;
    }
}
