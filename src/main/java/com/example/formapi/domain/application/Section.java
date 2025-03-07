package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "SECTION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Boolean autoValidated;

    @OneToOne
    @JoinColumn(name = "FK_PREV_SECTION_ID")
    private Section previousSection; //this section can be completed once the previous was completed / validated

    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionField> sectionFields = new ArrayList<>();

    public void setSectionFields(List<SectionField> sectionFields) {
        this.sectionFields.clear();
        if (sectionFields != null) {
            sectionFields.forEach(this::addSectionField);
        }
    }

    public void addSectionField(SectionField field) {
        sectionFields.add(field);
        field.setSection(this); // Maintain bidirectional consistency
    }
}

