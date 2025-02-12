package com.example.formapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    //    private final AuthEntryPointJwt unauthorizedHandler;
//    private final UserService userService;
//    private final JwtService jwtService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        User user = new User();
//        user.getUsername();
//        return new AuthTokenFilter(jwtService, userService);// AuthTokenFilter(jwtService, userService);
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf((csrf) -> csrf.disable())
//                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(registry ->
//                        registry.anyRequest().permitAll()
//                                .anyRequest().authenticated()); //todo
//
//        http.authenticationProvider(authenticationProvider());
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowCredentials(true) //pot primi cookie-uri -> access-control-allow-credentials: true
//                        .allowedOriginPatterns("http://localhost:4200") //access-control-allow-origin: http://localhost:4200
//                        .allowedMethods("GET", "POST", "PUT", "DELETE"); //access-control-allow-methods: GET POST PUT DELETE
//            }
//        };
//    }
}
