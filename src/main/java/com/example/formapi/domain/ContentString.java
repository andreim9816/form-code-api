package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT_STRING")
public class ContentString {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToOne(mappedBy = "contentString")
    private SectionEntry sectionEntry;
}
