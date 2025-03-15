package com.example.formapi.domain.application.validation;

import com.example.formapi.domain.application.SectionField;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "DATE_VALIDATOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateValidator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IS_REQUIRED")
    private boolean isRequired;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DATE_TIME")
    private DateTime dateTime;

    @OneToMany(mappedBy = "dateValidator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionField> sectionFields;
}
