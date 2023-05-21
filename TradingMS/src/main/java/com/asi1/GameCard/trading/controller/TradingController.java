package com.asi1.GameCard.trading.controller;

import com.asi1.GameCard.trading.model.Trading;
import com.asi1.GameCard.trading.service.TradingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradingController {

    private final TradingService tradingService;

    public TradingController(TradingService tradingService) {
        this.tradingService = tradingService;
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
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.badRequest().body("Failed to sell the card.");
        }
    }
}
