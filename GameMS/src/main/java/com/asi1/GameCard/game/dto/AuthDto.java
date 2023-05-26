// AuthDTO.java
package com.asi1.GameCard.game.dto;

import java.util.List;

public class AuthDto {
    private Long id;
    private String login;
    private double account;
    private String lastName;
    private String surName;
    private String email;
    private List<Long> cardList;

    // Les getters et setters restent inchangés

    public AuthDto() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public double getAccount() {
        return account;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }

    public List<Long> getCardList() {
        return cardList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // public void setPwd(String pwd) {

    // }

    public void setAccount(double account) {
        this.account = account;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCardList(List<Long> cardList) {
        this.cardList = cardList;
    }

    public void addCard(Long cardId) {
        this.cardList.add(cardId);
    }

    public void removeCard(Long cardId) {
        this.cardList.remove(cardId);
    }

    @Override
    public String toString() {
        return "AuthDto [id=" + id + ", login=" + login + ", account=" + account + ", lastName=" + lastName
                + ", surName=" + surName + ", email=" + email + ", cardList=" + cardList + "]";
    }

    public void setPwd(String password) {
    }

}