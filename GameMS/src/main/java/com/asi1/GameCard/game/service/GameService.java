package com.asi1.GameCard.game.service;

import com.asi1.GameCard.game.model.Game;
import com.asi1.GameCard.game.model.Room;
import com.asi1.GameCard.game.repository.GameRepository;
import com.asi1.GameCard.game.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(String name, Double bet) {
        Room room = new Room();
        room.setName(name);
        room.setBet(bet);
        return roomRepository.save(room);
    }

    public Game startGame(Long roomId, Long cardId1, Long cardId2) {
        // Implement game starting logic here

        Game game = new Game();
        game.setRoomId(roomId);
        game.setCardId1(cardId1);
        game.setCardId2(cardId2);
        // game.setWinnerId(...); This should be set based on game logic

        return gameRepository.save(game);
    }
}
