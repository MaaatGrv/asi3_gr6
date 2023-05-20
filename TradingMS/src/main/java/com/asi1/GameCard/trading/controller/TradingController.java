package com.asi1.GameCard.trading.controller;

import com.asi1.GameCard.trading.model.Trading;
import com.asi1.GameCard.trading.service.TradingService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.asi1.GameCard.auth.service.UserService;

@RestController
public class TradingController {

    private final TradingService tradingService;
    private final UserService userService;

    @Autowired
    public TradingController(TradingService tradingService, UserService userService) {
        this.tradingService = tradingService;
        this.userService = userService;
    }

    @PostMapping("/buy-card")
    public ResponseEntity<?> buyCard(@RequestParam("userId") Long userId, @RequestParam("cardId") Long cardId) {
        Trading transaction = tradingService.buyCard(userId, cardId);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.badRequest().body("Failed to buy the card.");
        }
    }

    @PostMapping("/sell-card")
    public ResponseEntity<?> sellCard(@RequestParam("userId") Long userId, @RequestParam("cardId") Long cardId) {
        Trading transaction = tradingService.sellCard(userId, cardId);
        if (transaction != null) {
            // Récupérez le solde mis à jour de l'utilisateur
            Double updatedAccountBalance = userService.findUserById(userId).get().getAccount();

            // Créez une Map pour inclure le solde mis à jour de l'utilisateur dans la
            // réponse
            Map<String, Object> response = new HashMap<>();
            response.put("transaction", transaction);
            response.put("updatedAccountBalance", updatedAccountBalance);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Failed to sell the card.");
        }
    }
}
