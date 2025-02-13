package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "SECTION_ENTRY")
public class SectionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LastModifiedDate
    private Date addedDate;

    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;

    @OneToOne
    @JoinColumn(name = "FK_CONTENT_BOOLEAN_ID")
    private ContentBoolean contentBoolean;

    @OneToOne
    @JoinColumn(name = "FK_CONTENT_DATE_ID")
    private ContentDate contentDate;

    @OneToOne
    @JoinColumn(name = "FK_CONTENT_NUMBER_ID")
    private ContentNumber contentNumber;

    @OneToOne
    @JoinColumn(name = "FK_CONTENT_STRING_ID")
    private ContentString contentString;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "FK_FORM_ID")
    private Form form;
}
