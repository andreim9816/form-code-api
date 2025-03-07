package com.example.formapi.service;

import com.example.formapi.domain.application.Section;
import com.example.formapi.domain.application.SectionField;
import com.example.formapi.domain.application.Template;
import com.example.formapi.domain.application.User;
import com.example.formapi.dto.UserDto;
import com.example.formapi.dto.input.ReqSectionDto;
import com.example.formapi.dto.input.ReqSectionFieldDto;
import com.example.formapi.dto.input.ReqTemplateDto;
import com.example.formapi.repository.application.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class Mapper {

    private final CompanyRepository companyRepository;

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userType(user.getUserType())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }


    //without builder works, to check !!
    @Transactional
    public Template toEntity(ReqTemplateDto dto) {
        var template = new Template();
        var sections = new ArrayList<Section>();

        template.setTitle(dto.getTitle());
        template.setDescription(dto.getDescription());
        template.setCompany(companyRepository.getReferenceById(dto.getCompanyId()));

        for (ReqSectionDto sectionDto : dto.getSections()) {
            var section = new Section();
            section.setTitle(dto.getTitle());
            section.setTemplate(template); // FK

            var sectionFields = new ArrayList<SectionField>();
            for (ReqSectionFieldDto sectionFieldDto : sectionDto.getSectionFields()) {
                var sectionField = new SectionField();
                sectionField.setContentType(sectionFieldDto.getContentType());
                sectionField.setAddedDate(new Date());
                sectionFields.add(sectionField);
            }
            section.setSectionFields(sectionFields);
            sections.add(section);
        }
        template.setSections(sections);

        return template;
//        return Template.builder()
//                .title(dto.getTitle())
//                .description(dto.getDescription())
//                .company(companyRepository.getReferenceById(dto.getCompanyId()))
//                .sections(dto.getSections().stream().map(this::toEntity).collect(Collectors.toList()))
//                .build();
    }

    public Section toEntity(ReqSectionDto dto) {
        return Section.builder()
                .title(dto.getTitle())
                .sectionFields(dto.getSectionFields().stream().map(this::toEntity).collect(Collectors.toList()))
                .build();
    }

    public SectionField toEntity(ReqSectionFieldDto dto) {
        return SectionField.builder()
                .contentType(dto.getContentType())
                .build();
    }
//  public User toEntity(RegisterDto dto) {
//    return User.builder()
//      .username(dto.getUsername())
//      .bankAccounts(Collections.emptyList())
//      .email(dto.getEmail())
//      .firstname(dto.getFirstName())
//      .lastname(dto.getLastName())
//      .email(dto.getEmail())
//      .phoneNumber(dto.getPhoneNumber())
//      .password(dto.getPassword())
//      .build();
//  }
}
