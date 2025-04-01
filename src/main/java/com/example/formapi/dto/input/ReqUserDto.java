package com.example.formapi.dto.input;

import com.example.formapi.domain.enumeration.UserType;
import lombok.Data;

import java.util.List;

@Data
public class ReqUserDto {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private String phoneNumber;

    private List<UserType> userTypes;

    private List<Long> companyIds;

    private List<Long> companyRoleIds;
}
