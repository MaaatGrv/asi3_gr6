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
        // TODO: Check if both players have joined the room

        return null;
    }
}
