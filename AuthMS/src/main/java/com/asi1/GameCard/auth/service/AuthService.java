package com.asi1.GameCard.auth.service;

import com.asi1.GameCard.auth.model.Auth;
import com.asi1.GameCard.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository userRepository;

    @Autowired
    public AuthService(AuthRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Auth createUser(Auth user) {
        return userRepository.save(user);
    }

    public Optional<Auth> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(Auth updatedUser) {
        userRepository.save(updatedUser);
    }

    public Optional<Auth> findUserByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

    public Auth updateUser(Long id, Auth user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Auth> getAllUsers() {
        return (List<Auth>) userRepository.findAll();
    }
}
