package com.asi1.GameCard.game.service;

import com.asi1.GameCard.game.model.Room;
import com.asi1.GameCard.game.repository.RoomRepository;
import com.asi1.GameCard.game.dto.UserDto;
import com.asi1.GameCard.game.dto.CardDto;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class GameService {

    private final RoomRepository roomRepository;
    private final RestTemplate restTemplate;

    public GameService(RoomRepository roomRepository, RestTemplate restTemplate) {
        this.roomRepository = roomRepository;
        this.restTemplate = restTemplate;
    }

    public Long playGame(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && !room.isOpen() && room.getPlayerIds().size() == 2) {
            // TODO: implement the game logic here, considering the room's players and their
            // selected cards.
            // The game logic should determine the winner and return the winner's user ID.
            // For the purpose of this example, we will just return the ID of the first
            // player.

            // After the game ends, you may want to reset the room to be open again and
            // remove the player IDs and card IDs.
            room.setOpen(true);
            room.getPlayerIds().clear();
            room.getCardIds().clear();
            roomRepository.save(room);

            return room.getPlayerIds().get(0);
        }

        return null;
    }
}
