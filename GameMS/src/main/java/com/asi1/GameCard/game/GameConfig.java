package com.asi1.GameCard.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GameConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
