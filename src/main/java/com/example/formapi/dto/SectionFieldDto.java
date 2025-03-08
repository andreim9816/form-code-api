package com.example.formapi.dto;

import com.example.formapi.domain.application.ContentType;
import lombok.Data;

import java.util.Date;

@Data
public class SectionFieldDto {
    private Long id;
    private Date addedDate;
    private ContentType contentType;
    private String defaultValue;
    private Long sectionId;
}
