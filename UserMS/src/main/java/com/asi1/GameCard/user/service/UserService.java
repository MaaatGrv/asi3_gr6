package com.asi1.GameCard.user.service;

import com.asi1.GameCard.user.model.User;
import com.asi1.GameCard.user.repository.UserRepository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asi1.GameCard.user.client.AuthClient;
import com.asi1.GameCard.user.dto.AuthDto;
import com.asi1.GameCard.user.dto.CardDto;

import java.net.http.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthClient authClient;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, AuthClient authClient, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.authClient = authClient;
        this.restTemplate = restTemplate;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    // You can add methods here to communicate with Auth microservice using
    // authClient

    public AuthDto getLoggedInUser() {
        return authClient.getLoggedInUser();
    }

    public void logout() {
        authClient.logout();
    }

    public List<CardDto> getCardsByUserId(Long userId) {
        ResponseEntity<List<CardDto>> response = restTemplate.exchange(
                "http://localhost:8090/card/user/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardDto>>() {
                });

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Gérer le cas où la requête échoue
            return new ArrayList<>();
        }
    }

}