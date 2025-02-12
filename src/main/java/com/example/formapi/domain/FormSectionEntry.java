package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "FORM_SECTION_ENTRY")
public class FormSectionEntry {

    @Id
    private Long id;

    private Date date;

    @OneToOne
    @JoinColumn(name = "FK_FIELD_CONTENT_ID")
    private FieldContent fieldContent;

    @ManyToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "FK_FORM_ID")
    private Form form;
}
