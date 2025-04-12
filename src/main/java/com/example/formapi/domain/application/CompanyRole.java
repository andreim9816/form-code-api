package com.example.formapi.domain.application;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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

    @Column(name = "CREATE_TEMPLATE")
    private boolean createTemplate;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_COMPANY_ID")
    private Company company;

    @ManyToMany(mappedBy = "companyRoles")
    private List<Section> sections = new ArrayList<>();

    @ManyToMany(mappedBy = "companyRoles")
    private List<User> users = new ArrayList<>();

    public CompanyRole addSection(Section section) {
        sections.add(section);
        section.getCompanyRoles().add(this);
        return this;
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section.getCompanyRoles().remove(this);
    }

    public CompanyRole addUser(User user) {
        users.add(user);
        user.getCompanyRoles().add(this);
        return this;
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getCompanyRoles().remove(this);
    }
}
