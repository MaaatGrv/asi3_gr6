// Card.java
package com.asi1.GameCard.cards.model;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String family;

    @Column(nullable = false)
    private String affinity;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String smallImgUrl;

    @Column(nullable = false)
    private int energy;

    @Column(nullable = false)
    private int hp;

    @Column(nullable = false)
    private int defence;

    @Column(nullable = false)
    private int attack;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Long userId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Card setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Card setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Card setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getAffinity() {
        return affinity;
    }

    public Card setAffinity(String affinity) {
        this.affinity = affinity;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Card setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public Card setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
        return this;
    }

    public int getEnergy() {
        return energy;
    }

    public Card setEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    public int getHp() {
        return hp;
    }

    public Card setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public int getDefence() {
        return defence;
    }

    public Card setDefence(int defence) {
        this.defence = defence;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public Card setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Card setPrice(int price) {
        this.price = price;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Card setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    // Constructors

    public Card() {
    }

    public Card(String name, String description, String family, String affinity, String imgUrl, String smallImgUrl,
            int energy, int hp, int defence, int attack, int price, Long userId) {
        this.name = name;
        this.description = description;
        this.family = family;
        this.affinity = affinity;
        this.imgUrl = imgUrl;
        this.smallImgUrl = smallImgUrl;
        this.energy = energy;
        this.hp = hp;
        this.defence = defence;
        this.attack = attack;
        this.price = price;
        this.userId = userId;
    }
}