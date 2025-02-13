package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT_NUMBER")
public class ContentNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long content;

    @OneToOne(mappedBy = "contentNumber")
    private SectionEntry sectionEntry;
}
