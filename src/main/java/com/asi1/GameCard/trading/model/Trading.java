package com.asi1.GameCard.trading.model;

import javax.persistence.*;

@Entity
public class Trading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long cardId;
    private String type;

    public Trading(Long userId, Long cardId, String type) {
        this.userId = userId;
        this.cardId = cardId;
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
