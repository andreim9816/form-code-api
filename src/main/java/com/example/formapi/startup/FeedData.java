package com.example.formapi.startup;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.User;
import com.example.formapi.repository.application.CompanyRepository;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.repository.application.UserRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void run(String... args) throws Exception {
        List<Company> companies = addCompanies();
        List<CompanyRole> companyRoles1 = addRolesToCompany(companies.get(0), new ArrayList<>(List.of("Inspector grad 1", "Inspector grad 2")));
        List<CompanyRole> companyRoles2 = addRolesToCompany(companies.get(1), new ArrayList<>(List.of("Rol 1", "Rol 2", "Rol 3")));
        List<User> users = addUsers();

        addUserAdminToCompany(users.get(1), companies.get(0));
        addUserAdminToCompany(users.get(3), companies.get(1));

        addCompanyRolesToComplianceUsers(users.get(1), companyRoles1);
        addCompanyRolesToComplianceUsers(users.get(2), new ArrayList<>(companyRoles1.subList(0, 1)));
        addCompanyRolesToComplianceUsers(users.get(3), companyRoles2);
        addCompanyRolesToComplianceUsers(users.get(4), new ArrayList<>(companyRoles2.subList(0, 2)));

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
                .isAdmin(true)
                .build();

        User user2 = User.builder()
                .email("user2@gmail.com")
                .firstname("firstname 2")
                .lastname("lastname 2")
                .username("user2")
                .password(passwordEncoder.encode("password2"))
                .isAdmin(false)
                .build();

        User user3 = User.builder()
                .email("user3@gmail.com")
                .firstname("firstname 3")
                .lastname("lastname 3")
                .username("user3")
                .password(passwordEncoder.encode("password3"))
                .isAdmin(false)
                .build();

        User user4 = User.builder()
                .email("user4@gmail.com")
                .firstname("firstname 4")
                .lastname("lastname 4")
                .username("user4")
                .password(passwordEncoder.encode("password4"))
                .isAdmin(false)
                .build();

        User user5 = User.builder()
                .email("user5@gmail.com")
                .firstname("firstname 5")
                .lastname("lastname 5")
                .username("user5")
                .password(passwordEncoder.encode("password5"))
                .isAdmin(false)
                .build();
        return userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
    }

    private User addCompanyRolesToComplianceUsers(User user, List<CompanyRole> companyRoles) {
        user.setCompanyRoles(companyRoles);
        return userRepository.save(user);
    }

    private Company addUserAdminToCompany(User user, Company company) {
        company.getAdminUsers().add(user);
        return companyRepository.save(company);
    }
}
