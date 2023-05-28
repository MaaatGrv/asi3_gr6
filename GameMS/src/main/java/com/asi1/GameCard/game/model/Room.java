package com.asi1.GameCard.game.model;

import java.util.List;

import javax.persistence.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private String roomName;

    private double bet;

    private Long userID1;

    private Long cardID1;

    private Long userID2;

    private Long cardID2;

    private Long winner;

    private Long looser;

    private boolean isOpen = true;

    public Long getRoomId() {
        return roomId;
    }

    public Room setRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getRoomName() {
        return roomName;
    }

    public Room setRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public double getBet() {
        return bet;
    }

    public Room setBet(double bet) {
        this.bet = bet;
        return this;
    }

    public Long getUserID1() {
        return userID1;
    }

    public Room setUserID1(Long userID1) {
        this.userID1 = userID1;
        return this;
    }

    public Long getUserID2() {
        return userID2;
    }

    public Room setUserID2(Long userID2) {
        this.userID2 = userID2;
        return this;
    }

    public Long getWinner() {
        return winner;
    }

    public Room setWinner(Long winner) {
        this.winner = winner;
        return this;
    }

    public Long getLooser() {
        return looser;
    }

    public Room setLooser(Long looser) {
        this.looser = looser;
        return this;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Room setOpen(boolean open) {
        this.isOpen = open;
        return this;
    }

    public Long getCardID1() {
        return cardID1;
    }

    public Room setCardID1(Long cardID1) {
        this.cardID1 = cardID1;
        return this;
    }

    public Long getCardID2() {
        return cardID2;
    }

    public Room setCardID2(Long cardID2) {
        this.cardID2 = cardID2;
        return this;
    }
}
