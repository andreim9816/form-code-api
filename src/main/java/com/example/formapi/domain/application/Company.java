package com.example.formapi.domain.application;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<Template> templates;

    @OneToMany(mappedBy = "company")
    private List<CompanyRole> companyRoles;

    @ManyToMany
    @JoinTable(name = "user_company",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
