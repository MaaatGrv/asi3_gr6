package com.asi1.GameCard.game.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double bet;
    private Long creatorId;

    @ElementCollection
    private List<Long> playerIds;

    @ElementCollection
    private List<Long> cardIds;

    private boolean isOpen;

    // getters and setters

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public double getBet() {
        return bet;
    }

    public Room setBet(double bet) {
        this.bet = bet;
        return this;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Room setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public List<Long> getPlayerIds() {
        return playerIds;
    }

    public Room setPlayerIds(List<Long> playerIds) {
        this.playerIds = playerIds;
        return this;
    }

    public List<Long> getCardIds() {
        return cardIds;
    }

    public Room setCardIds(List<Long> cardIds) {
        this.cardIds = cardIds;
        return this;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Room setOpen(boolean open) {
        isOpen = open;
        return this;
    }
}
