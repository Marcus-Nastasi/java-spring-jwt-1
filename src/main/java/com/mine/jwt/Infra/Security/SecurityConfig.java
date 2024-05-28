package com.mine.jwt.Infra.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth
                        .requestMatchers(HttpMethod.GET, "/api/adm").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/users/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/delete/{id}").hasRole("ADMIN")
                        .anyRequest().permitAll();
                })
                .build();
    }
}



