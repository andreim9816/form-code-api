package com.example.formapi.domain.application;

import jakarta.persistence.*;

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
}
