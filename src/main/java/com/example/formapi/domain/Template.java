package com.example.formapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TEMPLATE")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "FK_COMPANY_ID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;

    @OneToMany(mappedBy = "template")
    private List<Form> forms;

    @OneToMany(mappedBy = "template")
    private List<Section> sections;
}
