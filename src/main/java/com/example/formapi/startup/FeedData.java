package com.example.formapi.startup;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.User;
import com.example.formapi.domain.enumeration.UserType;
import com.example.formapi.repository.application.CompanyRepository;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.repository.application.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("add-data")
public class FeedData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final CompanyRoleRepository companyRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Company> companies = addCompanies();
        List<CompanyRole> companyRoles1 = addRolesToCompany(companies.get(0), List.of("Inspector grad 1", "Inspector grad 2"));
        List<CompanyRole> companyRoles2 = addRolesToCompany(companies.get(1), List.of("Rol 1", "Rol 2", "Rol 3"));
        List<User> users = addUsers();

        addUserToCompany(users.get(2), companies.get(0));
        addUserToCompany(users.get(3), companies.get(1));

        addCompanyRolesToComplianceUsers(users.get(2), companyRoles1);
        addCompanyRolesToComplianceUsers(users.get(3), companyRoles2);

    }

    private List<Company> addCompanies() {
        Company company1 = new Company();
        company1.setName("ANAF");

        Company company2 = new Company();
        company2.setName("Primaria Roman");
        return companyRepository.saveAll(List.of(company1, company2));
    }

    private List<CompanyRole> addRolesToCompany(Company company, List<String> roles) {
        List<CompanyRole> saved = new ArrayList<>();

        for (String role : roles) {
            CompanyRole companyRole = new CompanyRole();
            companyRole.setName(role);
            companyRole.setCompany(company);
            saved.add(companyRoleRepository.save(companyRole));
        }
        return saved;
    }

    private List<User> addUsers() {
        User user1 = User.builder()
                .email("user1@gmail.com")
                .firstname("firstname 1")
                .lastname("lastname 1")
                .username("user1")
                .password(passwordEncoder.encode("password1"))
                .userType(UserType.ADMIN)
                .build();

        User user2 = User.builder()
                .email("user2@gmail.com")
                .firstname("firstname 2")
                .lastname("lastname 2")
                .username("user2")
                .password(passwordEncoder.encode("password2"))
                .userType(UserType.COMPANY_ADMIN)
                .build();

        User user3 = User.builder()
                .email("user3@gmail.com")
                .firstname("firstname 3")
                .lastname("lastname 3")
                .username("user3")
                .password(passwordEncoder.encode("password3"))
                .userType(UserType.COMPLIANCE)
                .build();

        User user4 = User.builder()
                .email("user4@gmail.com")
                .firstname("firstname 4")
                .lastname("lastname 4")
                .username("user4")
                .password(passwordEncoder.encode("password4"))
                .userType(UserType.COMPLIANCE)
                .build();

        User user5 = User.builder()
                .email("user4@gmail.com")
                .firstname("firstname 5")
                .lastname("lastname 5")
                .username("user5")
                .password(passwordEncoder.encode("password5"))
                .userType(UserType.USER)
                .build();
        return userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
    }

    private User addCompanyRolesToComplianceUsers(User user, List<CompanyRole> companyRoles) {
        user.setCompanyRoles(companyRoles);
        return userRepository.save(user);
    }

    private Company addUserToCompany(User user, Company company) {
        company.getUsers().add(user);
        return companyRepository.save(company);
    }
}
