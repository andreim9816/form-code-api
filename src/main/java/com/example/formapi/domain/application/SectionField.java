package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Entity
@Table(name = "SECTION_FIELD")
public class SectionField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LastModifiedDate
    private Date addedDate;

    @Column(name = "CONTENT_TYPE")
    private ContentType contentType;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FK_SECTION_ID")
    private Section section;
}
