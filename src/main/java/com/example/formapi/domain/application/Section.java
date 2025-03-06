package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "SECTION")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Boolean autoValidated;

    @OneToOne
    @JoinColumn(name = "FK_PREV_SECTION_ID")
    private Section previousSection; //this section can be completed once the previous was completed / validated

    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @OneToMany(mappedBy = "section")
    private List<SectionField> sectionFields;
}

