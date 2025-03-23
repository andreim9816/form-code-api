package com.example.formapi.security;

import com.example.formapi.domain.application.User;
import com.example.formapi.repository.application.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSecuritySupport {
    private final UserRepository userRepository;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        //todo
        return userRepository.findById(3L).orElseThrow();
//        return null;
    }
}
