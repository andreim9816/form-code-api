package com.example.formapi.domain.client;

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

//    @OneToOne(mappedBy = "contentBoolean")
    @Column(name = "SECTION_ENTRY_ID")
    private Long sectionEntryId;
}
