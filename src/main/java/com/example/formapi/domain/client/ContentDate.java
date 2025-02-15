package com.example.formapi.domain.client;

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

    //    @OneToOne(mappedBy = "contentBoolean")
    @Column(name = "SECTION_ENTRY_ID")
    private Long sectionEntryId;
}
