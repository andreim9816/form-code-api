package com.example.formapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<Template> templates;

    @OneToMany(mappedBy = "company")
    private List<User> users;
}
