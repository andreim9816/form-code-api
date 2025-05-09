package com.example.formapi.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String cnp;
    private Date dateOfBirth;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
//    private AddressDto address;
    private String address;
    private Boolean isAdmin;
    private List<CompanyDto> companies;
    private List<CompanyRoleDto> companyRoles;
}
