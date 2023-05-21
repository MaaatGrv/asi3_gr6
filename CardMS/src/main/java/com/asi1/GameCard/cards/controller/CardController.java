package com.asi1.GameCard.cards.controller;

import com.asi1.GameCard.cards.model.Card;
import com.asi1.GameCard.cards.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@GetMapping("/cards")
	public ResponseEntity<List<Card>> getAllCards() {
		List<Card> cards = cardService.getAllCards();
		return ResponseEntity.ok(cards);
	}

	@GetMapping("/card/{id}")
	public ResponseEntity<Card> getCard(@PathVariable Long id) {
		Card card = cardService.getCard(id);
		return ResponseEntity.ok(card);
	}

	@PostMapping("/card")
	public ResponseEntity<Card> createCard(@RequestBody Card card) {
		Card savedCard = cardService.createCard(card);
		return ResponseEntity.ok(savedCard);
	}

	@PutMapping("/card/{id}")
	public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card) {
		Card updatedCard = cardService.updateCard(id, card);
		return ResponseEntity.ok(updatedCard);
	}

	@DeleteMapping("/card/{id}")
	public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
		cardService.deleteCard(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user/{userId}/cards")
	public ResponseEntity<List<Card>> getCardsByUserId(@PathVariable Long userId) {
		List<Card> cards = cardService.getCardsByUserId(userId);
		return ResponseEntity.ok(cards);
	}

	@GetMapping("/cards_to_sell")
	public ResponseEntity<List<Card>> getCardsForSale() {
		List<Card> cards = cardService.getCardsForSale();
		return ResponseEntity.ok(cards);
	}

}