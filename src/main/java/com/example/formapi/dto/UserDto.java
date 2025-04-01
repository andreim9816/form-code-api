package com.example.formapi.dto;

import com.example.formapi.domain.enumeration.UserType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private List<UserType> userTypes;
}
