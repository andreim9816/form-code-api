package com.example.formapi.startup;

import com.example.formapi.domain.application.Company;
import com.example.formapi.domain.application.User;
import com.example.formapi.domain.application.UserType;
import com.example.formapi.repository.application.CompanyRepository;
import com.example.formapi.repository.application.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("add-data")
public class FeedData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
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
                .userType(UserType.COMPANY_MANAGER)
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
                .userType(UserType.USER)
                .build();
        userRepository.saveAll(List.of(user1, user2, user3, user4));

        Company company = new Company();
        company.setName("ANAF");
        companyRepository.save(company);
    }
}
