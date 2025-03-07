package com.example.formapi.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class ReqSectionDto {
    private String title;
    private List<ReqSectionFieldDto> sectionFields;
}
