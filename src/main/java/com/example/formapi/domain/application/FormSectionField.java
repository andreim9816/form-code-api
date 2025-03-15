package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "FORM_SECTION_FIELD")
public class FormSectionField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentStringId;

    private Boolean contentBooleanId;

    private Date contentDateId;

    private Long contentNumberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SECTION_FIELD_ID")
    private SectionField sectionField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FORM_SECTION_ID")
    private FormSection formSection;
}
