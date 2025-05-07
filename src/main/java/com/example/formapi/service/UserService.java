package com.example.formapi.service;

import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.User;
import com.example.formapi.dto.input.ReqUserDto;
import com.example.formapi.exception.CustomException;
import com.example.formapi.exception.InvalidEntityException;
import com.example.formapi.mapper.UserMapper;
import com.example.formapi.ocr.OcrService;
import com.example.formapi.repository.application.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final OcrService ocrService;
    private final CompanyRoleService companyRoleService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException("Invalid user id"));
    }

    public User updateRoles(User user, List<Long> companyRoleIds) {
        Set<CompanyRole> companyRoles = companyRoleIds.stream()
                .map(companyRoleService::findById)
                .collect(Collectors.toSet());
        user.setCompanyRoles(companyRoles);
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public User registerUser(ReqUserDto userDto) {
        isUsernameUnique(userDto.getUsername());
        isEmailUnique(userDto.getEmail());
        isPhoneUnique(userDto.getPhoneNumber());
        User entity;
        try {
            String address = ocrService.extractAddress();
            String cnp = ocrService.extractCnp();
            String firstName = ocrService.extractFirstName();
            String lastName = ocrService.extractLastName();

            userDto.setCnp(cnp);
            userDto.setFirstname(firstName);
            userDto.setLastname(lastName);
            userDto.setAddress(address);

            entity = userMapper.toEntity(userDto);
        } catch (ParseException e) {
            throw new CustomException("Cannot parse CNP");
        }
        return userRepository.save(entity);
    }

//    public User registerUser(RegisterDto body) throws CustomException {
//        isEmailUnique(body.getEmail());
//        isPhoneUnique(body.getPhoneNumber());
//        isUsernameUnique(body.getUsername());
//
//        User entity = mapper.toEntity(body);
//        entity.setPassword(passwordEncoder.encode(body.getPassword()));
//
//        return userRepository.save(entity);
//    }

    public void isEmailUnique(String email) throws CustomException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new CustomException("Email already exists!");
        }
    }

    public void isPhoneUnique(String phone) throws CustomException {
        if (userRepository.findByPhoneNumber(phone).isPresent()) {
            throw new CustomException("Phone number already exists!");
        }
    }

    public void isUsernameUnique(String username) throws CustomException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomException("Username already exists!");
        }
    }
}
