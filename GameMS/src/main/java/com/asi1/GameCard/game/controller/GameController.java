package com.asi1.GameCard.game.controller;

import com.asi1.GameCard.game.model.Game;
import com.asi1.GameCard.game.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game/start/{roomId}")
    public ResponseEntity<Game> startGame(@PathVariable Long roomId) {
        Game game = gameService.startGame(roomId);
        if (game != null) {
            return ResponseEntity.ok(game);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        Game game = gameService.getGameById(gameId);
        if (game != null) {
            return ResponseEntity.ok(game);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/game/attack/{gameId}")
    public ResponseEntity<?> attack(@PathVariable Long gameId, @RequestParam Long userId) {
        Game game = gameService.attack(gameId, userId);
        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.badRequest().body("Attack failed.");
        }
    }
}
