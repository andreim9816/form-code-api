package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", unique = true, nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Template> templates = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompanyRole> companyRoles = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "user_company",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> adminUsers = new ArrayList<>();
}
