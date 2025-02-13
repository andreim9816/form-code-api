package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT_BOOLEAN")
public class ContentBoolean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean content;

    @OneToOne(mappedBy = "contentBoolean")
    private SectionEntry sectionEntry;
}
