package com.example.formapi.controller;

import com.example.formapi.domain.application.User;
import com.example.formapi.dto.LoginDto;
import com.example.formapi.dto.UserDto;
import com.example.formapi.security.JwtService;
import com.example.formapi.security.WebSecuritySupport;
import com.example.formapi.service.Mapper;
import com.example.formapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.formapi.security.JwtService.API_PATH;
import static com.example.formapi.security.JwtService.COOKIE_KEY;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final WebSecuritySupport webSecuritySupport;
    private final JwtService jwtService;
    private final UserService userService;
    private final Mapper mapper;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginDto body) {
        Authentication authentication = authenticate(body);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = webSecuritySupport.getUser();
        String token = jwtService.generateToken(user);

        ResponseCookie responseCookie = ResponseCookie.from(COOKIE_KEY, token)
                .path(API_PATH)
                .maxAge(7 * 24 * 60 * 60)
                .httpOnly(true)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(mapper.toDto(user));
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto body) throws CustomException {
//        User newUser = userService.registerUser(body);
//
//        return ResponseEntity.ok()
//                .body(newUser);
//    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from(COOKIE_KEY, null)
                .path(API_PATH)
                .maxAge(0)
                .httpOnly(true)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    private Authentication authenticate(LoginDto loginDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    }

}
