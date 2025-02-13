package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CONTENT_DATE")
public class ContentDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date content;

    @OneToOne(mappedBy = "contentDate")
    private SectionEntry sectionEntry;
}
