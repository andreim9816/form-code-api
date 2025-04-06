package com.example.formapi.controller;

import com.example.formapi.domain.application.User;
import com.example.formapi.dto.UserDto;
import com.example.formapi.mapper.UserMapper;
import com.example.formapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable("userId") Long userId) {
        return userMapper.toDto(userService.findById(userId));
    }

    @PatchMapping("/{userId}/roles")
    public UserDto update(@PathVariable Long userId, @RequestBody List<Long> companyRoleIds) {
        User user = userService.findById(userId);
        user = userService.updateRoles(user, companyRoleIds);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userService.findById(userId));
    }
}
