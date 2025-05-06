package com.example.formapi.domain.application;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    //todo maybe firstname, lastname, cnp, dateOfBirth, phoneNumber should be moved to dedicated table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String cnp;

    private Date dateOfBirth;

    private String firstname;

    private String lastname;

    private String email;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "IS_ADMIN")
    private Boolean isAdmin;

    @ManyToMany(mappedBy = "adminUsers")
    private Set<Company> companies = new HashSet<>(); // companies where this user is COMPANY_ADMIN

    @ManyToMany
    @JoinTable(
            name = "user_company_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_role_id")
    )
    private Set<CompanyRole> companyRoles = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "creatorUser")
    private List<Template> templates; //list of forms created by COMPLIANCE users

    @ToString.Exclude
    @OneToMany(mappedBy = "currentUser")
    private List<Form> startedForms; //list of forms started by USER users

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}
