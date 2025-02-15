package com.example.formapi.dto;

import com.example.formapi.domain.application.UserType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private UserType userType;
}
