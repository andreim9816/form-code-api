package com.example.formapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

    public static final String USERNAME_KEY = "username";
    public static final String COOKIE_KEY = "cookie";
    public static final String API_PATH = "/api";
    private static final int EXPIRED_TIME = 60;

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

    public String generateTokenFromUsername(String username) {
        return JWT.create()
                .withClaim(USERNAME_KEY, username)
                .withExpiresAt(Instant.now().plus(EXPIRED_TIME, ChronoUnit.MINUTES))
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
