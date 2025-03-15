package com.example.formapi.controller;

import com.example.formapi.dto.FormDto;
import com.example.formapi.mapper.FormMapper;
import com.example.formapi.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/forms")
public class FormController {

    private final FormService formService;
    private final FormMapper formMapper;

    @GetMapping("/{formId}")
    public FormDto findById(@PathVariable("formId") Long formId) {
        return formMapper.toDto(formService.findById(formId));
    }

    @DeleteMapping("/{formId}")
    public void delete(@PathVariable Long formId) {
        formService.deleteById(formId);
    }
}
