package com.jwt.jwtlearningauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.filter.DelegatingFilterProxy;

@SpringBootApplication
public class JwtLearningAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtLearningAuthServerApplication.class, args);
    }
}
