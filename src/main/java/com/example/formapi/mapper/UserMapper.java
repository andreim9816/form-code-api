package com.example.formapi.mapper;

import com.example.formapi.domain.application.User;
import com.example.formapi.dto.UserDto;
import com.example.formapi.dto.input.ReqUserDto;
import com.example.formapi.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final CompanyRoleMapper companyRoleMapper;
    private final CompanyMapper companyMapper;

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .dateOfBirth(user.getDateOfBirth())
                .cnp(user.getCnp())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isAdmin(user.getIsAdmin())
                .companies(user.getCompanies().stream().map(companyMapper::toDto).collect(Collectors.toList()))
                .companyRoles(user.getCompanyRoles().stream().map(companyRoleMapper::toDto).collect(Collectors.toList()))
                .build();
    }

    public User toEntity(ReqUserDto dto) throws ParseException {
        return User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .cnp(dto.getCnp())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .dateOfBirth(DateUtils.extractBirthDateFromCNP(dto.getCnp()))
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .isAdmin(false)
                .build();
    }
}

