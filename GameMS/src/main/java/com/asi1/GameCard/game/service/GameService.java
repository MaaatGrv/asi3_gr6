package com.asi1.GameCard.game.service;

import com.asi1.GameCard.game.dto.CardDto;
import com.asi1.GameCard.game.model.Game;
import com.asi1.GameCard.game.repository.GameRepository;
import com.asi1.GameCard.game.repository.RoomRepository;
import com.asi1.GameCard.game.model.Room;

import java.io.Console;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final RoomRepository roomRepository;

    private final RestTemplate restTemplate;

    public GameService(GameRepository gameRepository, RoomRepository roomRepository, RestTemplate restTemplate) {
        this.gameRepository = gameRepository;
        this.roomRepository = roomRepository;
        this.restTemplate = restTemplate;
    }

    public Game startGame(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Check if room is open
        if (room.isOpen()) {
            throw new RuntimeException("Room is not available");
        }

        // Check if game already started
        Game existingGame = getGameByRoomId(roomId);
        if (existingGame != null) {
            throw new RuntimeException("Game already started in this room");
        }

        // Retrieve users' cards
        String cardServiceUrl = "http://host.docker.internal:8090/card"; // Change this to your actual Card API Gateway
                                                                         // URL

        ResponseEntity<CardDto> card1Response = restTemplate.getForEntity(cardServiceUrl + "/" + room.getCardID1(),
                CardDto.class);
        CardDto card1 = card1Response.getBody();

        ResponseEntity<CardDto> card2Response = restTemplate.getForEntity(cardServiceUrl + "/" + room.getCardID2(),
                CardDto.class);
        CardDto card2 = card2Response.getBody();

        // Check if cards have enough energy to play
        int minEnergy = 4; // Replace with your actual minimum energy required
        if (card1.getEnergy() < minEnergy || card2.getEnergy() < minEnergy) {
            throw new RuntimeException("One or both cards do not have enough energy to play");
        }

        // Create a new instance of Game with initial values
        Game game = new Game();
        game.setRoomId(roomId);
        game.setCardID1(room.getCardID1());
        game.setCardID2(room.getCardID2());
        game.setCardID1CurrentHP(card1.getHp());
        game.setCardID2CurrentHP(card2.getHp());
        game.setCardID1Energy(card1.getEnergy());
        game.setCardID2Energy(card2.getEnergy());
        game.setCurrentPlayerId(room.getUserID1()); // Player 1 starts first (change this if you want player 2 to start
                                                    // first

        // Save the new Game instance to database
        gameRepository.save(game);

        // Put the gameId in the room
        if (room.getGameId() == null) {
            room.setGameId(game.getGameId());
            roomRepository.save(room);
        }

        return game;
    }

    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    public Game attack(Long gameId, Long userId) {
        // Retrieve the game from the id
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Find the room
        Room room = roomRepository.findById(game.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Check if the user is in the game
        if (!room.getUserID1().equals(userId) && !room.getUserID2().equals(userId)) {
            throw new RuntimeException("User is not in the game");
        }

        // Determine which card belongs to the user
        boolean isUserCard1 = room.getUserID1().equals(userId);
        Long attackerCardId = isUserCard1 ? game.getCardID1() : game.getCardID2();
        Long defenderCardId = isUserCard1 ? game.getCardID2() : game.getCardID1();

        // Check if it's the user's turn
        if (!game.getCurrentPlayerId().equals(userId)) {
            throw new RuntimeException("It's not your turn");
        }

        // Retrieve the attacker card and the defender card
        CardDto attackerCard = getCard(attackerCardId);
        CardDto defenderCard = getCard(defenderCardId);

        // Check if the attacker card has enough energy to attack
        if ((isUserCard1 ? game.getCardID1Energy() : game.getCardID2Energy()) < 1) {
            throw new RuntimeException("Attacker card does not have enough energy to attack");
        }

        // Calculate the damage
        int damage = calculateDamage(attackerCard, defenderCard);

        // Apply the damage
        if (isUserCard1) {
            game.setCardID2CurrentHP(game.getCardID2CurrentHP() - damage);
        } else {
            game.setCardID1CurrentHP(game.getCardID1CurrentHP() - damage);
        }

        // Update the energy
        if (isUserCard1) {
            game.setCardID1Energy(game.getCardID1Energy() - 1);
        } else {
            game.setCardID2Energy(game.getCardID2Energy() - 1);
        }

        // Check if the game is over
        if (game.getCardID1CurrentHP() <= 0 || game.getCardID2CurrentHP() <= 0) {
            if (game.getCardID1CurrentHP() <= 0) {
                // If card 1 is dead, player 2 wins
                game.setWinnerId(room.getUserID2());
            }
            if (game.getCardID2CurrentHP() <= 0) {
                // If card 2 is dead, player 1 wins
                game.setWinnerId(room.getUserID1());
            }
            game.setIsOver(true);
        } else {
            // If the game is not over, switch the current player
            if (game.getCurrentPlayerId().equals(room.getUserID1())) {
                game.setCurrentPlayerId(room.getUserID2());
            } else {
                game.setCurrentPlayerId(room.getUserID1());
            }
        }

        // Save the updated Game instance to database
        game = gameRepository.save(game);

        return game;
    }

    private CardDto getCard(Long cardId) {
        ResponseEntity<CardDto> cardResponse = restTemplate.getForEntity(
                "http://host.docker.internal:8090/card/" + cardId,
                CardDto.class);
        return cardResponse.getBody();
    }

    private int calculateDamage(CardDto attackerCard, CardDto defenderCard) {
        // Define the basic attack power
        int basicAttack = attackerCard.getAttack();

        // Check the family affinity
        if (attackerCard.getFamily().equals("Eau") && defenderCard.getFamily().equals("Feu")
                || attackerCard.getFamily().equals("Feu") && defenderCard.getFamily().equals("Nature")
                || attackerCard.getFamily().equals("Nature") && defenderCard.getFamily().equals("Eau")) {
            // If the attacker has the advantageous family, increase the attack power
            basicAttack *= 1.2;
        }

        // Check if the attack is critical
        if (new Random().nextDouble() < 0.1) {
            // If the attack is critical, increase the attack power
            basicAttack *= 1.5;
        }

        return basicAttack;
    }

    // Find the game by the room id
    public Game getGameByRoomId(Long roomId) {
        for (Game game : gameRepository.findAll()) {
            if (game.getRoomId().equals(roomId)) {
                return game;
            }
        }
        return null;
    }
}
