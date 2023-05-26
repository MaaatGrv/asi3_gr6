package com.asi1.GameCard.game.controller;

import com.asi1.GameCard.game.service.GameService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/play/{roomId}")
    public ResponseEntity<?> playGame(@PathVariable Long roomId) {
        Long winnerUserId = gameService.playGame(roomId);
        if (winnerUserId != null) {
            return ResponseEntity.ok("The game has ended. The winner is user with ID: " + winnerUserId);
        } else {
            return ResponseEntity.badRequest()
                    .body("Failed to play the game. Ensure all players are ready and the game is started.");
        }
    }
}
