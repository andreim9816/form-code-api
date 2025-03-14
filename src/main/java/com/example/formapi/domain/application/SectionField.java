package com.example.formapi.domain.application;

import com.example.formapi.domain.application.validation.DateValidator;
import com.example.formapi.domain.application.validation.NumberValidator;
import com.example.formapi.domain.application.validation.TextValidator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    /////////////////////////////// validators
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_TEXT_ID")
    private TextValidator textValidator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_NUMBER_ID")
    private NumberValidator numberValidator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_DATE_ID")
    private DateValidator dateValidator;

    /////////////////////////////// validators

    @OneToMany(mappedBy = "sectionField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSectionField> formSectionFields = new ArrayList<>();

    public void addFormSectionField(FormSectionField field) {
        formSectionFields.add(field);
        field.setSectionField(this); // Maintain bidirectional consistency
    }

    @Override
    public int compareTo(SectionField o) {
        return this.id.compareTo(o.id);
    }
}
