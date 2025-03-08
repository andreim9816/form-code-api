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

    //    @OneToOne(mappedBy = "contentBoolean")
    @Column(name = "SECTION_ENTRY_ID")
    private Long sectionEntryId;
}
