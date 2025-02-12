package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "SECTION")
public class Section {

    @Id
    private Long id;

    private String title;

    private String content;

    @OneToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section previousSection; //this section can be completed once the previous was completed / validated

    private UserType userType; // user role that can modify this section

    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @OneToMany(mappedBy = "section")
    private List<FormSectionEntry> formSectionEntries;
}

