package com.example.formapi.mapper;

import com.example.formapi.domain.application.User;
import com.example.formapi.dto.UserDto;
import com.example.formapi.repository.application.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CompanyRepository companyRepository;

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userTypes(user.getUserTypes())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

//  public User toEntity(RegisterDto dto) {
//    return User.builder()
//      .username(dto.getUsername())
//      .bankAccounts(Collections.emptyList())
//      .email(dto.getEmail())
//      .firstname(dto.getFirstName())
//      .lastname(dto.getLastName())
//      .email(dto.getEmail())
//      .phoneNumber(dto.getPhoneNumber())
//      .password(dto.getPassword())
//      .build();
//  }
}

