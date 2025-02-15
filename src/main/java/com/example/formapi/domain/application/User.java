package com.example.formapi.domain.application;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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

    private UserType userType;

    @ManyToMany(mappedBy = "users")
    private List<Company> companies;

    @ManyToMany(mappedBy = "users")
    private List<CompanyRole> companyRoles;

    @OneToMany(mappedBy = "user")
    private List<SectionEntry> sectionEntries;

    @OneToMany(mappedBy = "user")
    private List<Template> templates; //list of forms created by COMPLIANCE users

    @OneToMany(mappedBy = "user")
    private List<Form> startedForms; //list of forms started by USER users

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(convertUserTypeToString(userType));
        return List.of(authority);
    }

    public static String convertUserTypeToString(UserType userType) {
        String role = null;
        switch (userType) {
            case ADMIN -> {
                role = "ROLE_ADMIN";
            }
            case COMPANY_MANAGER -> {
                role = "ROLE_COMPANY_MANAGER";
            }
            case COMPLIANCE -> {
                role = "ROLE_COMPLIANCE";
            }
            case USER -> {
                role = "ROLE_USER";
            }
        }
        return role;
    }
}
