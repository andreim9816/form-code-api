package com.example.formapi.domain.application;

import com.example.formapi.domain.application.validation.DateValidator;
import com.example.formapi.domain.application.validation.NumberValidator;
import com.example.formapi.domain.application.validation.TextValidator;
import com.example.formapi.domain.enumeration.ContentType;
import com.example.formapi.domain.enumeration.PersonalDataType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "SECTION_FIELD")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionField implements Comparable<SectionField> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @CreatedDate
//    private Date addedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    /////////////////////////////// validators
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_TEXT_ID")
    private TextValidator textValidator;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_NUMBER_ID")
    private NumberValidator numberValidator;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATE_ID")
    private DateValidator dateValidator;

    /////////////////////////////// validators

    @ToString.Exclude
    @OneToMany(mappedBy = "sectionField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSectionField> formSectionFields = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "PERSONAL_DATA_TYPE")
    private PersonalDataType personalDataType;

    public void addFormSectionField(FormSectionField field) {
        formSectionFields.add(field);
        field.setSectionField(this); // Maintain bidirectional consistency
    }

    @Override
    public int compareTo(SectionField o) {
        return this.id.compareTo(o.id);
    }
}
