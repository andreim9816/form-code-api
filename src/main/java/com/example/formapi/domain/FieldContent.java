package com.example.formapi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "FIELD_CONTENT")
public class FieldContent {

    @Id
    private Long id;

    private Object content; // could be boolean, number, string, date

    @Column(name = "TYPE")
    private ContentType contentType;

    @OneToOne(mappedBy = "fieldContent")
    private FormSectionEntry formSectionEntry;
}
