package com.example.formapi.startup;

import com.example.formapi.domain.application.Address;
import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.User;
import com.example.formapi.repository.application.CompanyRepository;
import com.example.formapi.repository.application.CompanyRoleRepository;
import com.example.formapi.repository.application.UserRepository;
import com.example.formapi.utils.DateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public void run(String... args) throws ParseException {
        List<Company> companies = addCompanies();// companyRepository.findAll();
        Set<CompanyRole> companyRoles1 = addRolesToCompany(companies.get(0), List.of("Inspector grad 1", "Inspector grad 2"));
        Set<CompanyRole> companyRoles2 = addRolesToCompany(companies.get(1), List.of("Rol 1", "Rol 2", "Rol 3"));
        List<User> users = addUsers();// userRepository.findAll();

        addUserAdminToCompany(users.get(1), companies.get(0));
        addUserAdminToCompany(users.get(3), companies.get(1));

        addCompanyRolesToComplianceUsers(users.get(1), companyRoles1);
        addCompanyRolesToComplianceUsers(users.get(2), getCompanyRolesByName("Inspector grad 1"));
        addCompanyRolesToComplianceUsers(users.get(3), companyRoles2);
        addCompanyRolesToComplianceUsers(users.get(4), getCompanyRolesByName("Rol 1", "Rol 2"));

    }

    private Set<CompanyRole> getCompanyRolesByName(String... names) {
        Set<CompanyRole> companyRoles = new HashSet<>();
        for (String name : names) {
            Optional<CompanyRole> companyRole = companyRoleRepository.findByName(name);
            companyRole.ifPresent(companyRoles::add);
        }
        return companyRoles;
    }

    private List<Company> addCompanies() {
        Company company1 = new Company();
        company1.setName("ANAF");

        Company company2 = new Company();
        company2.setName("Primaria Roman");
        return companyRepository.saveAll(List.of(company1, company2));
    }

    private Set<CompanyRole> addRolesToCompany(Company company, List<String> roles) {
        Set<CompanyRole> saved = new HashSet<>();

        for (String role : roles) {
            CompanyRole companyRole = new CompanyRole();
            companyRole.setName(role);
            companyRole.setCompany(company);
            saved.add(companyRoleRepository.save(companyRole));
        }
        return saved;
    }

    private List<User> addUsers() throws ParseException {
        Address address1 = Address.builder()
                .street("Gloriei")
                .block("B1")
                .entrance("C")
                .apartment(55L)
                .city("Buzau")
                .county("Buzau")
                .build();

        Address address2 = Address.builder()
                .street("Tineretului")
                .block("A")
                .entrance("B")
                .apartment(4L)
                .city("Craiova")
                .county("Dolj")
                .build();

        Address address3 = Address.builder()
                .street("Economu Cezarescu")
                .no("34-42")
                .block("1")
                .entrance("1")
                .apartment(45L)
                .city("Sector 6")
                .county("Bucuresti")
                .build();

        Address address4 = Address.builder()
                .street("Cuza Voda")
                .block("8")
                .entrance("A")
                .apartment(6L)
                .city("Roman")
                .county("Neamt")
                .build();

        Address address5 = Address.builder()
                .street("Trapezului")
                .block("12")
                .entrance("B2")
                .apartment(105L)
                .city("Sector 3")
                .county("Bucuresti")
                .build();

        User user1 = User.builder()
                .email("user1@gmail.com")
                .firstname("firstname 1")
                .lastname("lastname 1")
                .username("user1")
                .password(passwordEncoder.encode("password1"))
                .isAdmin(true)
                .cnp("1971126284712")
                .dateOfBirth(DateUtils.extractBirthDateFromCNP("1971126284712"))
                .address(address1)
                .build();

        User user2 = User.builder()
                .email("user2@gmail.com")
                .firstname("firstname 2")
                .lastname("lastname 2")
                .username("user2")
                .password(passwordEncoder.encode("password2"))
                .isAdmin(false)
                .cnp("1830303284712")
                .dateOfBirth(DateUtils.extractBirthDateFromCNP("1830303284712"))
                .address(address2)
                .build();

        User user3 = User.builder()
                .email("user3@gmail.com")
                .firstname("firstname 3")
                .lastname("lastname 3")
                .username("user3")
                .password(passwordEncoder.encode("password3"))
                .isAdmin(false)
                .cnp("1770513284712")
                .dateOfBirth(DateUtils.extractBirthDateFromCNP("1770513284712"))
                .address(address3)
                .build();

        User user4 = User.builder()
                .email("user4@gmail.com")
                .firstname("firstname 4")
                .lastname("lastname 4")
                .username("user4")
                .password(passwordEncoder.encode("password4"))
                .isAdmin(false)
                .cnp("2880102712342")
                .dateOfBirth(DateUtils.extractBirthDateFromCNP("2880102712342"))
                .address(address4)
                .build();

        User user5 = User.builder()
                .email("user5@gmail.com")
                .firstname("firstname 5")
                .lastname("lastname 5")
                .username("user5")
                .password(passwordEncoder.encode("password5"))
                .isAdmin(false)
                .cnp("2821217712342")
                .dateOfBirth(DateUtils.extractBirthDateFromCNP("2821217712342"))
                .address(address5)
                .build();

        return userRepository.saveAll(List.of(user1, user2, user3, user4, user5));
    }

    private User addCompanyRolesToComplianceUsers(User user, Set<CompanyRole> companyRoles) {
        user.setCompanyRoles(companyRoles);
        return userRepository.save(user);
    }

    private Company addUserAdminToCompany(User user, Company company) {
        company.getAdminUsers().add(user);
        return companyRepository.save(company);
    }
}
