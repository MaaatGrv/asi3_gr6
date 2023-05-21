package com.asi1.GameCard.trading.service;

import com.asi1.GameCard.trading.model.Trading;
import com.asi1.GameCard.trading.dto.UserDto;
import com.asi1.GameCard.trading.dto.CardDto;
import com.asi1.GameCard.trading.repository.TradingRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class TradingService {

    private final TradingRepository tradingRepository;
    private final RestTemplate restTemplate;

    public TradingService(TradingRepository tradingRepository, RestTemplate restTemplate) {
        this.tradingRepository = tradingRepository;
        this.restTemplate = restTemplate;
    }

    public Trading buyCard(Long userId, Long cardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<UserDto> userResponse = restTemplate.exchange("http://gateway/user/" + userId, HttpMethod.GET,
                entity, UserDto.class);
        ResponseEntity<CardDto> cardResponse = restTemplate.exchange("http://gateway/card/" + cardId, HttpMethod.GET,
                entity, CardDto.class);

        if (userResponse.getStatusCode() == HttpStatus.OK && cardResponse.getStatusCode() == HttpStatus.OK) {
            UserDto user = userResponse.getBody();
            CardDto card = cardResponse.getBody();

            if (user.getAccount() >= card.getPrice()) {
                user.setAccount(user.getAccount() - card.getPrice());
                user.getCardList().add(card.getId());
                card.setUserId(userId);

                restTemplate.put("http://gateway/user/", user, UserDto.class);
                restTemplate.put("http://gateway/card/", card, CardDto.class);

                Trading transaction = new Trading(userId, cardId, "buy");
                tradingRepository.save(transaction);
                return transaction;
            }
        }
        return null;
    }

    public Trading sellCard(Long userId, Long cardId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<UserDto> userResponse = restTemplate.exchange("http://gateway/user/" + userId, HttpMethod.GET,
                entity, UserDto.class);
        ResponseEntity<CardDto> cardResponse = restTemplate.exchange("http://gateway/card/" + cardId, HttpMethod.GET,
                entity, CardDto.class);

        if (userResponse.getStatusCode() == HttpStatus.OK && cardResponse.getStatusCode() == HttpStatus.OK) {
            UserDto user = userResponse.getBody();
            CardDto card = cardResponse.getBody();

            if (user.getCardList().contains(card.getId())) {
                user.setAccount(user.getAccount() + card.getPrice());
                user.getCardList().remove(card.getId());
                card.setUserId(0L);

                restTemplate.put("http://gateway/user/", user, UserDto.class);
                restTemplate.put("http://gateway/card/", card, CardDto.class);

                Trading transaction = new Trading(userId, cardId, "sell");
                tradingRepository.save(transaction);
                return transaction;
            }
        }
        return null;
    }
}
