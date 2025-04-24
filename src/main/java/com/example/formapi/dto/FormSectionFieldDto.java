package com.example.formapi.dto;

import com.example.formapi.dto.content.ContentDateDto;
import com.example.formapi.dto.content.ContentFileDto;
import com.example.formapi.dto.content.ContentNumberDto;
import com.example.formapi.dto.content.ContentStringDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormSectionFieldDto {
    private Long id;
    @JsonProperty("isBreakLine")
    private boolean isBreakLine;
    private Long formSectionId;
    private SectionFieldDto sectionField;

    private ContentStringDto contentString;
    private ContentNumberDto contentNumber;
    private ContentDateDto contentDate;
    private ContentFileDto contentFile;

    private Long contentBooleanId;
}
