package com.example.formapi.controller;

import com.example.formapi.repository.application.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/forms")
public class FormController {

    private final FormRepository formRepository;

    @DeleteMapping("/{formId}")
    public void delete(@PathVariable Long formId) {
        formRepository.deleteById(formId);
    }
}
