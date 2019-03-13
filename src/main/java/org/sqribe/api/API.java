package org.sqribe.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAuthorizationServer
@SpringBootApplication
@EnableTransactionManagement
public class API {
    public static void main(String[] args) {
        SpringApplication.run(API.class, args);
    }
}