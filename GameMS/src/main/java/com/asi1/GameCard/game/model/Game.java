package com.asi1.GameCard.game.model;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private Long roomId; // The room in which this game takes place

    private Long cardID1; // Card 1

    private Integer cardID1CurrentHP; // Current HP of Card 1

    private Integer cardID1Energy; // Energy of Card 1

    private Long cardID2; // Card 2

    private Integer cardID2CurrentHP; // Current HP of Card 2

    private Integer cardID2Energy; // Energy of Card 2

    private Long winnerId; // ID of the winning card

    private boolean isOver = false; // Whether the game is over

    private Long currentPlayerId; // ID of the current player

    // Getters and setters

    public Long getGameId() {
        return this.gameId;
    }

    public Game setGameId(Long gameId) {
        this.gameId = gameId;
        return this;
    }

    public Long getRoomId() {
        return this.roomId;
    }

    public Game setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public Long getCardID1() {
        return this.cardID1;
    }

    public Game setCardID1(Long cardID1) {
        this.cardID1 = cardID1;
        return this;
    }

    public Integer getCardID1CurrentHP() {
        return this.cardID1CurrentHP;
    }

    public Game setCardID1CurrentHP(Integer cardID1CurrentHP) {
        this.cardID1CurrentHP = cardID1CurrentHP;
        return this;
    }

    public Integer getCardID1Energy() {
        return this.cardID1Energy;
    }

    public Game setCardID1Energy(Integer cardID1Energy) {
        this.cardID1Energy = cardID1Energy;
        return this;
    }

    public Long getCardID2() {
        return this.cardID2;
    }

    public Game setCardID2(Long cardID2) {
        this.cardID2 = cardID2;
        return this;
    }

    public Integer getCardID2CurrentHP() {
        return this.cardID2CurrentHP;
    }

    public Game setCardID2CurrentHP(Integer cardID2CurrentHP) {
        this.cardID2CurrentHP = cardID2CurrentHP;
        return this;
    }

    public Integer getCardID2Energy() {
        return this.cardID2Energy;
    }

    public Game setCardID2Energy(Integer cardID2Energy) {
        this.cardID2Energy = cardID2Energy;
        return this;
    }

    public Long getWinnerId() {
        return this.winnerId;
    }

    public Game setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
        return this;
    }

    public boolean isIsOver() {
        return this.isOver;
    }

    public Game setIsOver(boolean isOver) {
        this.isOver = isOver;
        return this;
    }

    public Long getCurrentPlayerId() {
        return this.currentPlayerId;
    }

    public Game setCurrentPlayerId(Long currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
        return this;
    }
}