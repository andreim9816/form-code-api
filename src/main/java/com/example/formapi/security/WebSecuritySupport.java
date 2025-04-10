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
            User user = (User) authentication.getPrincipal();
            return userRepository.findById(user.getId()).get();
        }
        //todo
        return userRepository.findById(2L).orElseThrow();
//        return null;
    }
}
