package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "FORM_SECTION_FIELD")
public class FormSectionField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FK_CONTENT_STRING_ID")
    private Long contentStringId;

    @Column(name = "FK_CONTENT_BOOLEAN_ID")
    private Long contentBooleanId;

    @Column(name = "FK_CONTENT_DATE_ID")
    private Long contentDateId;

    @Column(name = "FK_CONTENT_NUMBER_ID")
    private Long contentNumberId;

    @Column(name = "IS_BREAKLINE", columnDefinition = "NUMBER(1) DEFAULT 0")
    private boolean isBreakLine = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SECTION_FIELD_ID")
    private SectionField sectionField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FORM_SECTION_ID")
    private FormSection formSection;
}
