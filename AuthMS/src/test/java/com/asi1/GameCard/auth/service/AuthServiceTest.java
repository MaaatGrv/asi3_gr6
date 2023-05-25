package com.asi1.GameCard.auth.service;

import com.asi1.GameCard.auth.model.Auth;
import com.asi1.GameCard.auth.repository.AuthRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private AuthRepository authRepository;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authRepository = Mockito.mock(AuthRepository.class);
    }

    @Test
    void findUserById() {
        Auth auth = new Auth();
        auth.setId(1L);

        when(authRepository.findById(1L)).thenReturn(Optional.of(auth));

        Optional<Auth> foundAuth = authService.findUserById(1L);

        assertEquals(foundAuth.get(), auth);
    }

    // Similar tests for other methods in AuthService
}
