package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "FORM")
public class Form {

    @Id
    private Long id;

    @CreatedDate // works??
    private Date startedOn;

    @LastModifiedDate
    private Date finishedOn;

    @ManyToOne
    @JoinColumn(name = "FK_TEMPLATE_ID")
    private Template template;

    @OneToMany(mappedBy = "form")
    private List<FormSectionEntry> formSectionEntries;
}
