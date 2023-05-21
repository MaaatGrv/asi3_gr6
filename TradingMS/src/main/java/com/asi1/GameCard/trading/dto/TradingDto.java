// AuthDTO.java
package com.asi1.GameCard.trading.dto;

public class TradingDto {
    private Long id;
    private Long userId;
    private Long cardId;
    private String type;

    // Les getters et setters restent inchangés

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setType(String type) {
        this.type = type;
    }
}