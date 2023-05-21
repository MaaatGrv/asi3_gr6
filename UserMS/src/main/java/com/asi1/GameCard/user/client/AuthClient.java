package com.asi1.GameCard.user.client;

import com.asi1.GameCard.user.dto.AuthDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {
    private final RestTemplate restTemplate;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public AuthClient() {
        this.restTemplate = new RestTemplate();
    }

    public AuthDto login(AuthDto authDto) {
        HttpEntity<AuthDto> requestEntity = new HttpEntity<>(authDto);
        ResponseEntity<AuthDto> response = restTemplate.exchange(
                authServiceUrl + "/auth", HttpMethod.POST, requestEntity, AuthDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }

    public AuthDto getLoggedInUser() {
        ResponseEntity<AuthDto> response = restTemplate.getForEntity(
                authServiceUrl + "/auth/user", AuthDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }

    public void logout() {
        restTemplate.postForLocation(authServiceUrl + "/auth/logout", null);
    }
}
