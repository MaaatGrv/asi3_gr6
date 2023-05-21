package com.asi1.GameCard.cards.dto;

import java.util.List;

public class UserDto {
    private List<Long> cardList;

    public UserDto() {
    }

    public UserDto(List<Long> cardList) {
        this.cardList = cardList;
    }

    public List<Long> getCardList() {
        return cardList;
    }

    public void setCardList(List<Long> cardList) {
        this.cardList = cardList;
    }
}