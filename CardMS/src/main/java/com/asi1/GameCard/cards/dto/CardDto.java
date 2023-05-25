package com.asi1.GameCard.cards.dto;

public class CardDto {
    private Long id;
    private String name;
    private String description;
    private String family;
    private String affinity;
    private String imgUrl;
    private String smallImgUrl;
    private int energy;
    private int hp;
    private int defence;
    private int attack;
    private int price;
    private Long userId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public CardDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CardDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public CardDto setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getAffinity() {
        return affinity;
    }

    public CardDto setAffinity(String affinity) {
        this.affinity = affinity;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public CardDto setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public CardDto setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
        return this;
    }

    public int getEnergy() {
        return energy;
    }

    public CardDto setEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    public int getHp() {
        return hp;
    }

    public CardDto setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public int getDefence() {
        return defence;
    }

    public CardDto setDefence(int defence) {
        this.defence = defence;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public CardDto setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public CardDto setPrice(int price) {
        this.price = price;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CardDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    // Constructors

    public CardDto() {
    }

    public CardDto(String name, String description, String family, String affinity, String imgUrl, String smallImgUrl,
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