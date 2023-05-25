package com.asi1.GameCard.user.service;

import com.asi1.GameCard.user.model.User;
import com.asi1.GameCard.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.asi1.GameCard.user.client.AuthClient;
import com.asi1.GameCard.user.dto.AuthDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthClient authClient;

    public UserService(UserRepository userRepository, AuthClient authClient) {
        this.userRepository = userRepository;
        this.authClient = authClient;
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
}