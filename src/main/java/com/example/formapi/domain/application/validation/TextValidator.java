package com.example.formapi.domain.application.validation;

import com.example.formapi.domain.application.SectionField;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TEXT_VALIDATOR")
public class TextValidator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IS_REQUIRED")
    private boolean isRequired;

    @Column(name = "MIN_SIZE")
    private Long minSize;

    @Column(name = "MAX_SIZE")
    private Long maxSize;

    @Column(name = "IS_EMAIL")
    private boolean isEmail;

    @Column(name = "IS_NO_SPACE")
    private boolean isNoSpace;

    @Column(name = "IS_NO_NUMBER")
    private boolean isNoNumber;

    @Column(name = "REGEX")
    private String regex;

    @OneToMany(mappedBy = "textValidator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionField> sectionFields;
}
