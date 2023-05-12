package com.asi1.GameCard.trading.service;

import com.asi1.GameCard.trading.model.Trading;
import com.asi1.GameCard.trading.repository.TradingRepository;
import com.asi1.GameCard.cards.model.Card;
import com.asi1.GameCard.cards.repository.CardRepository;
import com.asi1.GameCard.auth.model.User;
import com.asi1.GameCard.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradingService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final TradingRepository tradingRepository;

    @Autowired
    public TradingService(CardRepository cardRepository, UserRepository userRepository,
            TradingRepository tradingRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.tradingRepository = tradingRepository;
    }

    public Trading buyCard(Long userId, Long cardId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Card> card = cardRepository.findById(cardId);

        if (user.isPresent() && card.isPresent()) {
            // Vérifiez si l'utilisateur a suffisamment d'argent pour acheter la carte
            if (user.get().getAccount() >= card.get().getPrice()) {
                // Mettre à jour le solde du compte de l'utilisateur
                user.get().setAccount(user.get().getAccount() - card.get().getPrice());
                userRepository.save(user.get());

                // Ajoutez la carte à la liste des cartes de l'utilisateur
                user.get().getCardList().add(card.get().getId());
                userRepository.save(user.get());

                // Mettez à jour le userId de la carte et enregistrez la carte
                card.get().setUserId(userId);
                cardRepository.save(card.get());

                // Créez et retournez la transaction
                Trading transaction = new Trading(userId, cardId, "buy");
                tradingRepository.save(transaction); // Enregistrez la transaction
                return transaction;
            }
        }
        return null;
    }

    public Trading sellCard(Long userId, Long cardId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Card> card = cardRepository.findById(cardId);

        if (user.isPresent() && card.isPresent()) {
            // Vérifiez si l'utilisateur possède la carte
            if (user.get().getCardList().contains(card.get().getId())) {
                // Mettre à jour le solde du compte de l'utilisateur
                user.get().setAccount(user.get().getAccount() + card.get().getPrice());
                userRepository.save(user.get());

                // Retirez la carte de la liste des cartes de l'utilisateur
                user.get().getCardList().remove(card.get().getId());
                userRepository.save(user.get());

                // Réinitialisez le userId de la carte et enregistrez la carte
                card.get().setUserId(0L);
                cardRepository.save(card.get());

                // Créez et retournez la transaction
                Trading transaction = new Trading(userId, cardId, "sell");
                tradingRepository.save(transaction); // Enregistrez la transaction
                return transaction;
            }
        }
        return null;
    }

}
