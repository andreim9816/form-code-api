package com.example.formapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.formapi.domain.application.CompanyRole;
import com.example.formapi.domain.application.User;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;

@Service
@Slf4j
public class JwtService {

    public static final String ID_KEY = "id";
    public static final String USERNAME_KEY = "username";
    public static final String ROLES_KEY = "roles";
    public static final String USER_TYPE_KEY = "userType";
    public static final String COOKIE_KEY = "auth-cookie";
    public static final String API_PATH = "/api";
    private static final int TOKEN_TTL = 60;

    @Value("${HMAC_SECRET}")
    private String privateKeyStr;

    private Algorithm algorithm;

    @PostConstruct
    private void initKeys() {
        algorithm = Algorithm.HMAC512(privateKeyStr);
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, COOKIE_KEY);
        return cookie != null ? cookie.getValue() : null;
    }

    public String getUsernameFromJwtToken(String token) {
        String payload = validateToken(token);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> payloadMap = parser.parseMap(payload);

        return (String) payloadMap.get(USERNAME_KEY);
    }

    public String generateToken(User user) {
        return JWT.create()
                .withClaim(ID_KEY, user.getId())
                .withClaim(USERNAME_KEY, user.getUsername())
                .withClaim(ROLES_KEY, user.getCompanyRoles().stream().map(CompanyRole::getName).toList())
                .withClaim(USER_TYPE_KEY, user.getUserType().name())
                .withExpiresAt(Instant.now().plus(TOKEN_TTL, ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    public String validateToken(String token) throws JWTVerificationException {
        String encodedPayload = JWT.require(algorithm)
                .build()
                .verify(token)
                .getPayload();

        return new String(Base64.getDecoder().decode(encodedPayload));
    }

    public static Cookie generateEmptyCookie() {
        Cookie cookie = new Cookie(COOKIE_KEY, null);
        cookie.setMaxAge(0);
        cookie.setValue(null);
        cookie.setPath(API_PATH);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
