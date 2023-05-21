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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPwd() {
        return pwd;
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

    // public void setEmail(String email) {

    // }

    public void setCardList(List<Long> cardList) {
        this.cardList = cardList;
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

    public UserDto() {
    }

    @Override
    public String toString() {
        return "UserDto [account=" + account + ", cardList=" + cardList + ", email=" + email + ", id=" + id
                + ", lastName=" + lastName + ", login=" + login + ", pwd=" + pwd + ", surName=" + surName + "]";
    }
}
