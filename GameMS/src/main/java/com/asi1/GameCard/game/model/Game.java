package com.asi1.GameCard.game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cardId1;
    private Long cardId2;
    private Long winnerId;
    private Long roomId;

    // getters and setters

    public Game() {
    }

    public Game(Long cardId1, Long cardId2, Long winnerId, Long roomId) {
        this.cardId1 = cardId1;
        this.cardId2 = cardId2;
        this.winnerId = winnerId;
        this.roomId = roomId;
    }

    public Game(Long id, Long cardId1, Long cardId2, Long winnerId, Long roomId) {
        this.id = id;
        this.cardId1 = cardId1;
        this.cardId2 = cardId2;
        this.winnerId = winnerId;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public Long getCardId1() {
        return cardId1;
    }

    public Long getCardId2() {
        return cardId2;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardId1(Long cardId1) {
        this.cardId1 = cardId1;
    }

    public void setCardId2(Long cardId2) {
        this.cardId2 = cardId2;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
