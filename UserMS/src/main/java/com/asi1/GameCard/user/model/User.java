package com.asi1.GameCard.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String pwd;

    private double account = 1000;
    private String lastName;
    private String surName;
    private String email;

    // On ajoute aléatoirement 5 cartes par défaut à l'utilisateur pour qu'il puisse
    // jouer (1, 2,
    // 3, 4 , 5 ou 6)
    @ElementCollection
    @CollectionTable(name = "user_cards", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "card_id")
    private List<Long> cardList = List.of(1L, 2L, 3L, 4L, 5L);

    // Constructeurs, getters et setters

    public User() {
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
