package com.example.formapi.domain.application;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "FORM_SECTION")
public class FormSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "FK_FORM_ID")
    private Form form;

    @OneToMany(mappedBy = "formSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSectionField> formSectionFields;
}
