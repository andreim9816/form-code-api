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
    private String firstname;
    private String cnp;
    private Date dateOfBirth;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Boolean isAdmin;
    private AddressDto address;
    private List<CompanyDto> companies;
    private List<CompanyRoleDto> companyRoles;
}
