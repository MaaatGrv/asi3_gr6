package com.asi1.GameCard.game.service;

import com.asi1.GameCard.game.model.Room;
import com.asi1.GameCard.game.repository.RoomRepository;
import com.asi1.GameCard.game.dto.UserDto;
import com.asi1.GameCard.game.dto.CardDto;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RestTemplate restTemplate;

    public RoomService(RoomRepository roomRepository, RestTemplate restTemplate) {
        this.roomRepository = roomRepository;
        this.restTemplate = restTemplate;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public boolean joinRoom(Long roomId, Long userId, Long cardId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && room.isOpen() && room.getPlayerIds().size() < 2) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<UserDto> userResponse = restTemplate.exchange("http://localhost:8090/user/" + userId,
                    HttpMethod.GET,
                    entity, UserDto.class);
            ResponseEntity<CardDto> cardResponse = restTemplate.exchange("http://localhost:8090/card/" + cardId,
                    HttpMethod.GET,
                    entity, CardDto.class);

            if (userResponse.getStatusCode() == HttpStatus.OK && cardResponse.getStatusCode() == HttpStatus.OK) {
                UserDto user = userResponse.getBody();
                CardDto card = cardResponse.getBody();

                room.getPlayerIds().add(userId);
                room.getCardIds().add(cardId);
                roomRepository.save(room);
                return true;
            }
        }
        return false;
    }

    public boolean startGame(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && room.getPlayerIds().size() == 2) {
            room.setOpen(false);
            roomRepository.save(room);
            return true;
        }
        return false;
    }

    public List<Room> getAllRooms() {
        return (List<Room>) roomRepository.findAll();
    }
}
