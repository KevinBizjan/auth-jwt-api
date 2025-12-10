package com.auth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.auth.jwt.model")
public class AuthJwtApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthJwtApiApplication.class, args);
    }
}
