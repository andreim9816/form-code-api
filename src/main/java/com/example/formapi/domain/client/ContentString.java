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

    //    @OneToOne(mappedBy = "contentBoolean")
    @Column(name = "SECTION_ENTRY_ID")
    private Long sectionEntryId;
}
