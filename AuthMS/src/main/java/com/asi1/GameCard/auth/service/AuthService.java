package com.asi1.GameCard.auth.service;

import com.asi1.GameCard.auth.model.Auth;
import com.asi1.GameCard.auth.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Optional<Auth> findUserById(Long userId) {
        return authRepository.findById(userId);
    }

    public Optional<Auth> findUserByLogin(String login) {
        return Optional.ofNullable(authRepository.findByLogin(login));
    }
}