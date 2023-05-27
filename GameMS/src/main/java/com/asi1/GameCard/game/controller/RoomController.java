package com.asi1.GameCard.game.controller;

import com.asi1.GameCard.game.model.Room;
import com.asi1.GameCard.game.service.RoomService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/room/list")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        if (!rooms.isEmpty()) {
            return ResponseEntity.ok(rooms);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/room/create")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        if (createdRoom != null) {
            return ResponseEntity.ok(createdRoom);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/room/join/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable Long roomId, @RequestParam Long userId, @RequestParam Long cardId) {
        boolean success = roomService.joinRoom(roomId, userId, cardId);
        if (success) {
            return ResponseEntity.ok("Joined the room successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to join the room.");
        }
    }

    @PostMapping("/room/start/{roomId}")
    public ResponseEntity<?> startGame(@PathVariable Long roomId) {
        boolean success = roomService.startGame(roomId);
        if (success) {
            return ResponseEntity.ok("Game started successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to start the game.");
        }
    }
}
