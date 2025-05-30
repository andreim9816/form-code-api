package com.example.formapi.domain.application;

import com.example.formapi.domain.enumeration.FormSectionStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "FORM_SECTION")
public class FormSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private FormSectionStatus status;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FORM_ID")
    private Form form;

    @ToString.Exclude
    @OneToMany(mappedBy = "formSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSectionField> formSectionFields = new ArrayList<>();
}
