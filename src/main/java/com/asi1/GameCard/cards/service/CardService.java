package com.asi1.GameCard.cards.service;

import com.asi1.GameCard.cards.model.Card;
import com.asi1.GameCard.cards.repository.CardRepository;
import com.asi1.GameCard.auth.model.User;
import com.asi1.GameCard.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public Card getCard(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Card updateCard(Long id, Card updatedCard) {
        if (cardRepository.existsById(id)) {
            updatedCard.setId(id);
            return cardRepository.save(updatedCard);
        }
        return null;
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public Card createCard(Card newCard) {
        return cardRepository.save(newCard);
    }

    public List<Card> getCardsToSell() {
        // TODO: Implement this method
        return null;
    }

    public List<Card> getAllCards() {
        return (List<Card>) cardRepository.findAll();
    }

    public Optional<Card> findCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    public List<Card> getCardsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Long> cardList = user.getCardList();
            Iterable<Card> cards = cardRepository.findAllById(cardList);
            return StreamSupport.stream(cards.spliterator(), false).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<Card> getCardsForSale() {
        return cardRepository.findByUserId(0L);
    }
}
