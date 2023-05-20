package com.asi1.GameCard.user.service;

import com.asi1.GameCard.user.model.User;
import com.asi1.GameCard.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return Optional.ofNullable(userRepository.findByLogin(login));
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
}
