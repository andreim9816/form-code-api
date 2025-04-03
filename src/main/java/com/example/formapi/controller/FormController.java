package com.example.formapi.controller;

import com.example.formapi.dto.FormDto;
import com.example.formapi.exception.CustomException;
import com.example.formapi.mapper.FormMapper;
import com.example.formapi.security.WebSecuritySupport;
import com.example.formapi.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/forms")
public class FormController {
    private final WebSecuritySupport webSecuritySupport;
    private final FormService formService;
    private final FormMapper formMapper;

    @GetMapping
    public List<FormDto> findAll(
            @RequestParam(value = "createdByMe", required = false) boolean createdByMe,
            @RequestParam(value = "assignedToMe", required = false) boolean assignedToMe) {

        Long currentUserId = webSecuritySupport.getUser().getId();
        if (currentUserId == null) {
            throw new CustomException("No user found!");
        }
        var forms = formService.findALl().stream();
        if (createdByMe) {
            forms = forms.filter(form -> {
                Long formCreatorUserId = form.getCreatorUser() != null ? form.getCreatorUser().getId() : null;
                return currentUserId.equals(formCreatorUserId);
            });
        }
        if (assignedToMe) {
            forms = forms.filter(form -> {
                Long currentFormUserId = form.getCurrentUser() != null ? form.getCurrentUser().getId() : null;
                return currentUserId.equals(currentFormUserId);
            });
        }
        return forms.map(formMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{formId}")
    public FormDto findById(@PathVariable("formId") Long formId) {
        return formMapper.toDto(formService.findById(formId));
    }

    @DeleteMapping("/{formId}")
    public void delete(@PathVariable Long formId) {
        formService.delete(formService.findById(formId));
    }
}
