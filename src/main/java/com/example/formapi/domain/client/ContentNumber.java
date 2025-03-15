package com.example.formapi.domain.client;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT_NUMBER")
public class ContentNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long value;

    @Column(name = "FORM_SECTION_FIELD_ID")
    private Long formSectionFieldId;
}
