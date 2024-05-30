package com.mine.jwt.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mine.jwt.Models.Domain.Users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Bean
    public String generate(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return(
                JWT.create().withIssuer("auth-api")
                .withSubject(user.getEmail()).withExpiresAt(genExpDate())
                .sign(algorithm)
            );
        } catch (JWTCreationException e) {
            return "";
        }
    }

    @Bean
    public String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();

        } catch (JWTVerificationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Instant genExpDate() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"));
    }
}



