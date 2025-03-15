package com.example.formapi.domain.client;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT_STRING")
public class ContentString {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @Column(name = "FORM_SECTION_FIELD_ID")
    private Long formSectionFieldId;
}
