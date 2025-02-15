package com.example.formapi.domain.application;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "COMPANY_ROLE")
public class CompanyRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_COMPANY_ID")
    private Company company;

    @ManyToMany
    @JoinTable(name = "company_role",
            joinColumns = @JoinColumn(name = "company_role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
