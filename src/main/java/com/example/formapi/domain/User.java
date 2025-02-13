package com.example.formapi.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private String phoneNumber;

    @ManyToMany(mappedBy = "users")
    private List<Company> companies;

    @OneToMany(mappedBy = "user")
    private List<SectionEntry> sectionEntries;

    @OneToMany(mappedBy = "user")
    private List<Template> templates; //list of forms created by COMPLIANCE users

    @OneToMany(mappedBy = "user")
    private List<Form> startedForms; //list of forms started by USER users

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
