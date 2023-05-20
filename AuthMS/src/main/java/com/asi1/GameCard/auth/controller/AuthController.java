package com.asi1.GameCard.auth.controller;

import com.asi1.GameCard.auth.model.Auth;
import com.asi1.GameCard.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {

    private final AuthService userService;

    @Autowired
    public AuthController(AuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Auth> login(@RequestBody Auth loginUser, HttpServletRequest request) {
        Optional<Auth> user = userService.findUserByLogin(loginUser.getLogin());
        if (user.isPresent() && user.get().getPwd().equals(loginUser.getPwd())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/user")
    public ResponseEntity<Auth> register(@RequestBody Auth user) {
        Optional<Auth> existingUser = userService.findUserByLogin(user.getLogin());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Auth newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Auth> getUser(@PathVariable Long id) {
        Optional<Auth> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Auth> updateUser(@PathVariable Long id, @RequestBody Auth user) {
        Optional<Auth> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            Auth updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<Auth> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Auth>> getAllUsers() {
        List<Auth> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/auth/user")
    public ResponseEntity<Auth> getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Auth sessionUser = (Auth) session.getAttribute("user");
            if (sessionUser != null) {
                // Récupérez les informations à jour de l'utilisateur depuis la base de données
                Optional<Auth> dbUser = userService.findUserById(sessionUser.getId());
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

    @PutMapping("/user/{id}/addmoney")
    public ResponseEntity<Auth> addMoney(@PathVariable Long id, @RequestBody Double amount) {
        Optional<Auth> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            Auth user = existingUser.get();
            user.setAccount(user.getAccount() + amount);
            Auth updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}/removemoney")
    public ResponseEntity<Auth> removeMoney(@PathVariable Long id, @RequestBody Double amount) {
        Optional<Auth> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            Auth user = existingUser.get();
            user.setAccount(user.getAccount() - amount);
            Auth updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
