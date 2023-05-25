package com.asi1.GameCard.game.controller;

import com.asi1.GameCard.game.model.Game;
import com.asi1.GameCard.game.model.Room;
import com.asi1.GameCard.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/room")
    public Room createRoom(@RequestParam String name, @RequestParam Double bet) {
        return gameService.createRoom(name, bet);
    }

    @PostMapping("/start")
    public Game startGame(@RequestParam Long roomId, @RequestParam Long cardId1, @RequestParam Long cardId2) {
        return gameService.startGame(roomId, cardId1, cardId2);
    }
}
