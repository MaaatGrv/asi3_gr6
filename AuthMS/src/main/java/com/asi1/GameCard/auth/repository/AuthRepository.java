package com.asi1.GameCard.auth.repository;

import com.asi1.GameCard.auth.model.Auth;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Auth, Long> {
    Auth findByLogin(String login);
}
