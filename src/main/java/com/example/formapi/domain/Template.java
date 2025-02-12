package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TEMPLATE")
public class Template {

    @Id
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "FK_COMPANY_ID")
    private Company company;
    
    @OneToMany(mappedBy = "template")
    private List<Form> forms;
}
