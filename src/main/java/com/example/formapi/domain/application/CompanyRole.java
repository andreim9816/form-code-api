package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

    @ManyToMany(mappedBy = "companyRoles")
    private List<User> users = new ArrayList<>();
}
