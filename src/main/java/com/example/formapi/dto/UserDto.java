package com.example.formapi.dto;

import com.example.formapi.domain.enumeration.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private UserType userType;
}
