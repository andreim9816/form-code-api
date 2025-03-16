package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "TEMPLATE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_COMPANY_ID")
    private Company company;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private User creatorUser;

    @ToString.Exclude
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Form> forms = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    public void setSections(List<Section> sections) {
        this.sections.clear();
        if (sections != null) {
            sections.forEach(this::addSection);
        }
    }

    public void addSection(Section field) {
        sections.add(field);
        field.setTemplate(this);
    }
}
