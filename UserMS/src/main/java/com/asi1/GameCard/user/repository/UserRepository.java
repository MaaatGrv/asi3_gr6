package com.asi1.GameCard.user.repository;

import com.asi1.GameCard.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
