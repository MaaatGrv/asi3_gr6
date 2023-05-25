package com.asi1.GameCard.statics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StaticConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
