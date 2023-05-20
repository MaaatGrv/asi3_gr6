package com.asi1.GameCard.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
