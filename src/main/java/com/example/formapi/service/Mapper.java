package com.example.formapi.service;

import com.example.formapi.domain.User;
import com.example.formapi.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userType(user.getUserType())
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
