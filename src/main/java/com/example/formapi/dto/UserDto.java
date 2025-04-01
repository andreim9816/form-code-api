package com.example.formapi.dto;

import com.example.formapi.domain.enumeration.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String phoneNumber;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private List<UserType> userTypes;
}
