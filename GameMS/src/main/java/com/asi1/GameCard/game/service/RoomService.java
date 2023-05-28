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

    public boolean joinRoom(Long roomId, Long userId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && room.isOpen() && room.getUserID2() == null) { // Make sure the second player slot is empty
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<UserDto> userResponse = restTemplate.exchange("http://localhost:8090/user/" + userId,
                    HttpMethod.GET,
                    entity, UserDto.class);

            if (userResponse.getStatusCode() == HttpStatus.OK) {
                UserDto user = userResponse.getBody();

                room.setUserID2(userId); // Set the second player slot to this user
                room.setOpen(false); // Close the room since it's now full
                roomRepository.save(room);
                return true;
            }
        }
        return false;
    }

    public boolean startGame(Long roomId, Long cardId, Long userId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null && !room.isOpen() && room.getUserID1() != null && room.getUserID2() != null) {
            if (room.getUserID1().equals(userId)) {
                room.setCardID1(cardId);
            } else if (room.getUserID2().equals(userId)) {
                room.setCardID2(cardId);
            } else {
                return false; // User is not in this room
            }
            roomRepository.save(room);
            return true;
        }
        return false; // Room is not ready to start
    }

    public List<Room> getAllRooms() {
        return (List<Room>) roomRepository.findAll();
    }
}
