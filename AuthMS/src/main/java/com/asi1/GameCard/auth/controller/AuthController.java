package com.asi1.GameCard.auth.controller;

import com.asi1.GameCard.auth.model.Auth;
import com.asi1.GameCard.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Auth> login(@RequestBody Auth loginUser, HttpServletRequest request) {
        Optional<Auth> user = authService.findUserByLogin(loginUser.getLogin());
        if (user.isPresent() && user.get().getPwd().equals(loginUser.getPwd())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/auth/user")
    public ResponseEntity<Auth> getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Auth sessionUser = (Auth) session.getAttribute("user");
            if (sessionUser != null) {
                // Récupérez les informations à jour de l'utilisateur depuis la base de données
                Optional<Auth> dbUser = authService.findUserById(sessionUser.getId());
                if (dbUser.isPresent()) {
                    return ResponseEntity.ok(dbUser.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}