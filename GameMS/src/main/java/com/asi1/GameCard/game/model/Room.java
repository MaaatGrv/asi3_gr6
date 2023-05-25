package com.asi1.GameCard.game.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double bet;

    // getters and setters

    public Room() {
    }

    public Room(String name, Double bet) {
        this.name = name;
        this.bet = bet;
    }

    public Room(Long id, String name, Double bet) {
        this.id = id;
        this.name = name;
        this.bet = bet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBet() {
        return bet;
    }

    public void setName(String name2) {
    }

    public void setBet(Double bet2) {
    }
}
