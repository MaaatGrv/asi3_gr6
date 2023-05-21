package com.asi1.GameCard.cards.client;

import com.asi1.GameCard.cards.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<UserDto> getUserById(Long userId) {
        // Remplacez `user-service-url` par l'URL de votre service utilisateur.
        // Assurez-vous que le service utilisateur expose une API à cette URL qui
        // renvoie un UserDto.
        UserDto user = restTemplate.getForObject("http://user-service-url/api/users/" + userId, UserDto.class);
        return Optional.ofNullable(user);
    }
}
