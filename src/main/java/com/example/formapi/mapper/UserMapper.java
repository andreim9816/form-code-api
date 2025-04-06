package com.example.formapi.mapper;

import com.example.formapi.domain.application.User;
import com.example.formapi.dto.UserDto;
import com.example.formapi.dto.input.ReqUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final CompanyRoleMapper companyRoleMapper;
    private final CompanyMapper companyMapper;

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isAdmin(user.getIsAdmin())
                .companies(user.getCompanies().stream().map(companyMapper::toDto).toList())
                .companyRoles(user.getCompanyRoles().stream().map(companyRoleMapper::toDto).toList())
                .build();
    }

    public User toEntity(ReqUserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .cnp(dto.getCnp())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .isAdmin(false)
                .build();
    }
}

