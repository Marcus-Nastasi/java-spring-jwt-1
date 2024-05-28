package com.mine.jwt.Beans;

import com.mine.jwt.Models.Domain.Users.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansFactory {

    @Bean
    public User user() {
        return new User();
    }
}



