package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "FORM")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate // works??
    private Date createdDate;

    @LastModifiedDate
    private Date finishedDate;

    @OneToOne
    @JoinColumn(name = "FK_CURR_VALID_SECTION")
    private FormSection currentValidationSection;

    @OneToOne
    @JoinColumn(name = "FK_CURR_COMPLETE_SECTION")
    private FormSection currentSection;

    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User createdUser;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSection> formSections = new ArrayList<>();
}
