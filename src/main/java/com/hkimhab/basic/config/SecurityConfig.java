package com.hkimhab.basic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${swagger.auth.required:false}") // defaults to false if not set
    private boolean swaggerAuthRequired;

    private static final String[] PUBLIC_ENDPOINTS = {
            "/hello",
            "/hello/*",
            "/message",
            "/post-order",
            "/api/user",
            "/api/user/**",
            "/api/school",
            "/api/school/*",
            "/api/school/**",
            "/error",
            "/response/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(PUBLIC_ENDPOINTS).permitAll();
                    if (!swaggerAuthRequired) {
                        auth.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**").permitAll();
                    }
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }
}
