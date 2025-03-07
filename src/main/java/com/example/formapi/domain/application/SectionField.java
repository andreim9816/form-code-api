package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "SECTION_FIELD")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date addedDate;

    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    @OneToMany(mappedBy = "sectionField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSectionField> formSectionFields = new ArrayList<>();

    public void addFormSectionField(FormSectionField field) {
        formSectionFields.add(field);
        field.setSectionField(this); // Maintain bidirectional consistency
    }
}
