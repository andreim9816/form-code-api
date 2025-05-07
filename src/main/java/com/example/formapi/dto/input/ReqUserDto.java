package com.example.formapi.dto.input;

import lombok.Data;

@Data
public class ReqUserDto {

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    // will be null at request, but will be set by ocr service

    private String cnp;

    private String firstname;

    private String lastname;

    private String address;
}
