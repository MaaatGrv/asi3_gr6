package com.asi1.GameCard.auth.client;

import com.asi1.GameCard.auth.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {

    @Autowired
    private RestTemplate restTemplate;

    public AuthDto authenticateUser(String login, String password) {
        // remplacer par votre URL du service Auth
        String url = "http://localhost:8080/auth";
        AuthDto authUserDto = new AuthDto();
        authUserDto.setLogin(login);
        authUserDto.setPwd(password);
        return restTemplate.postForObject(url, authUserDto, AuthDto.class);
    }
}
