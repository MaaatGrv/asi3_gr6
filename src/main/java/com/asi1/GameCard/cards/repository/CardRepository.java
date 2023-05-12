package com.asi1.GameCard.cards.repository;

import com.asi1.GameCard.cards.model.Card;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByUserId(Long userId);
}
