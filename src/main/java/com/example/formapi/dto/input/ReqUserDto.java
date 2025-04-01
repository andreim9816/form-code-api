package com.example.formapi.dto.input;

import lombok.Data;

@Data
public class ReqUserDto {

    private String username;

    private String password;

    private String cnp;

    private String firstname;

    private String lastname;

    private String email;

    private String phoneNumber;
}
