package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "FORM")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate // works??
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate finishedDate;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "FK_CURR_VALID_SECTION")
    private FormSection currentValidationSection;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "FK_CURR_COMPLETE_SECTION")
    private FormSection currentSection;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "FK_CURRENT_USER_ID")
    private User currentUser;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CREATOR_USER_ID")
    private User creatorUser;

    @ToString.Exclude
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormSection> formSections = new ArrayList<>();
}
