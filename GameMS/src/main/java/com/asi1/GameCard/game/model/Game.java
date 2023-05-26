package com.asi1.GameCard.game.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roomId;

    @ElementCollection
    private List<Long> playerIds;

    @ElementCollection
    private List<Long> cardIds;

    @ElementCollection
    private List<Integer> cardHPs;

    private int totalMoves;

    private boolean isOngoing;

    private Long winnerId;

    // getters and setters

    public Long getId() {
        return id;
    }

    public Game setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Game setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public List<Long> getPlayerIds() {
        return playerIds;
    }

    public Game setPlayerIds(List<Long> playerIds) {
        this.playerIds = playerIds;
        return this;
    }

    public List<Long> getCardIds() {
        return cardIds;
    }

    public Game setCardIds(List<Long> cardIds) {
        this.cardIds = cardIds;
        return this;
    }

    public List<Integer> getCardHPs() {
        return cardHPs;
    }

    public Game setCardHPs(List<Integer> cardHPs) {
        this.cardHPs = cardHPs;
        return this;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public Game setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
        return this;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public Game setOngoing(boolean ongoing) {
        isOngoing = ongoing;
        return this;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public Game setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
        return this;
    }

    public Game() {
    }
}
