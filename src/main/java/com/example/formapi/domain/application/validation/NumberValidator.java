package com.example.formapi.domain.application.validation;

import com.example.formapi.domain.application.SectionField;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "NUMBER_VALIDATOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberValidator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IS_REQUIRED")
    private boolean isRequired;

    @Column(name = "MIN_VALUE")
    private Long minValue;

    @Column(name = "MAX_VALUE")
    private Long maxValue;

    @OneToMany(mappedBy = "numberValidator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionField> sectionFields;
}
