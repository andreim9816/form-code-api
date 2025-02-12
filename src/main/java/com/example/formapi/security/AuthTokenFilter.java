package com.example.formapi.security;

import com.example.formapi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.formapi.security.JwtService.generateEmptyCookie;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtService.getJwtFromCookies(request);
            if (jwt != null && jwtService.validateToken(jwt) != null) {
                String username = jwtService.getUsernameFromJwtToken(jwt);
                UserDetails user = userService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: ", e);
            Cookie cookie = generateEmptyCookie();
            response.addCookie(cookie);
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}

