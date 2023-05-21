package com.asi1.GameCard.trading.dto;

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

    // Les getters et setters restent inchangés

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getAffinity() {
        return affinity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHp() {
        return hp;
    }

    public int getDefence() {
        return defence;
    }

    public int getAttack() {
        return attack;
    }

    public int getPrice() {
        return price;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardDto(Long id, String name, String description, String family, String affinity, String imgUrl,
            String smallImgUrl, int energy, int hp, int defence, int attack, int price, Long userId) {
        this.id = id;
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

    public CardDto() {
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", family='" + family + '\'' +
                ", affinity='" + affinity + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", smallImgUrl='" + smallImgUrl + '\'' +
                ", energy=" + energy +
                ", hp=" + hp +
                ", defence=" + defence +
                ", attack=" + attack +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}
