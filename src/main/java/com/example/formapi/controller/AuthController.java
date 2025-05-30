package com.example.formapi.controller;

import com.example.formapi.domain.application.User;
import com.example.formapi.dto.LoginDto;
import com.example.formapi.dto.UserDto;
import com.example.formapi.dto.input.ReqUserDto;
import com.example.formapi.exception.CustomException;
import com.example.formapi.mapper.UserMapper;
import com.example.formapi.ocr.OcrService;
import com.example.formapi.security.JwtService;
import com.example.formapi.security.WebSecuritySupport;
import com.example.formapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    private final OcrService ocrService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/extract-fields")
    public String extractFields(@RequestParam("file") MultipartFile file) throws TesseractException, IOException {
        //not working do not use
        File tempFile = File.createTempFile("uploaded-", ".jpg");
        file.transferTo(tempFile);

//        String data = ocrService.extractFields(tempFile);
        String data = ocrService.extractAddress();
        tempFile.delete();
        return data;
    }

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
                .body(userMapper.toDto(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody ReqUserDto body) throws CustomException {
        User newUser = userService.registerUser(body);

        return ResponseEntity.ok()
                .body(userMapper.toDto(newUser));
    }

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
