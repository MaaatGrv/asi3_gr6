package com.asi1.GameCard.auth.controller;

import com.asi1.GameCard.auth.model.Auth;
import com.asi1.GameCard.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private AuthService authService;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        authService = Mockito.mock(AuthService.class);
        authController = new AuthController(authService);
    }

    @Test
    void login() {
        Auth auth = new Auth();
        auth.setId(1L);
        auth.setLogin("test");
        auth.setPwd("password");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(authService.findUserByLogin(auth.getLogin())).thenReturn(Optional.of(auth));

        ResponseEntity<Auth> response = authController.login(auth, request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), auth);
        verify(session).setAttribute("user", auth);
    }

    @Test
    void getLoggedInUser() {
        Auth auth = new Auth();
        auth.setId(1L);
        auth.setLogin("test");
        auth.setPwd("password");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(auth);
        when(authService.findUserById(auth.getId())).thenReturn(Optional.of(auth));

        ResponseEntity<Auth> response = authController.getLoggedInUser(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), auth);
    }

    @Test
    void logout() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        when(request.getSession(false)).thenReturn(session);

        ResponseEntity<Void> response = authController.logout(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(session).invalidate();
    }
}
