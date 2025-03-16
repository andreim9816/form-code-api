package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "SECTION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section implements Comparable<Section> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "IS_VALIDATION")
    private boolean isValidation;

    @ManyToMany
    @JoinTable(name = "section_role",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "company_role_id")
    )
    private List<CompanyRole> companyRoles;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @ToString.Exclude
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

    @Override
    public int compareTo(Section o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isValidation=" + isValidation +
                '}';
    }
}

