package com.hkimhab.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${swagger.auth.required:false}")  // defaults to false if not set
    private boolean swaggerAuthRequired;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> { 
            auth.requestMatchers("/hello", "/hello/*", "/message", "/post-order").permitAll();   
            if (!swaggerAuthRequired) {
                auth.requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll();
            }
            auth.anyRequest().authenticated();
        })
        .httpBasic(Customizer.withDefaults());
    return http.build();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}
