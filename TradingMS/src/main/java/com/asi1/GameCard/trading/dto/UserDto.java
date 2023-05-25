package com.asi1.GameCard.trading.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String login;
    private String pwd;
    private double account;
    private String lastName;
    private String surName;
    private String email;
    private List<Long> cardList;

    // Constructeurs, getters et setters

    public UserDto() {
    }

    public UserDto(Long id, String login, String pwd, double account, String lastName, String surName, String email,
            List<Long> cardList) {
        this.id = id;
        this.login = login;
        this.pwd = pwd;
        this.account = account;
        this.lastName = lastName;
        this.surName = surName;
        this.email = email;
        this.cardList = cardList;
    }

    // Ajoutez ici les autres constructeurs, getters et setters pour tous les
    // attributs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getCardList() {
        return cardList;
    }

    public void setCardList(List<Long> cardList) {
        this.cardList = cardList;
    }
}