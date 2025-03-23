package com.example.formapi.controller;

import com.example.formapi.dto.FormSectionDto;
import com.example.formapi.dto.input.ReqFormDto;
import com.example.formapi.mapper.FormSectionMapper;
import com.example.formapi.service.FormSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/forms/sections")
public class FormSectionController {

    private final FormSectionService formSectionService;
    private final FormSectionMapper formSectionMapper;

    @PatchMapping
    public List<FormSectionDto> updateForm(@RequestBody ReqFormDto formDto) {
        return formSectionService.update(formDto.getFormSections()).stream()
                .map(formSectionMapper::toDto)
                .collect(toList());
    }
}
