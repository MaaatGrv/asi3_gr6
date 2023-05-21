package com.asi1.GameCard.cards.service;

import com.asi1.GameCard.cards.client.UserClient;
import com.asi1.GameCard.cards.dto.UserDto;
import com.asi1.GameCard.cards.model.Card;
import com.asi1.GameCard.cards.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private CardService cardService;

    @Test
    public void testGetCardsByUserId() {
        List<Long> cardList = new ArrayList<>();
        cardList.add(1L);
        cardList.add(2L);

        UserDto user = new UserDto(cardList);
        when(userClient.getUserById(anyLong())).thenReturn(Optional.of(user));

        Card card1 = new Card();
        Card card2 = new Card();

        List<Card> expectedCards = Arrays.asList(card1, card2);
        when(cardRepository.findAllById(cardList)).thenReturn(expectedCards);

        List<Card> actualCards = cardService.getCardsByUserId(1L);

        assertEquals(2, actualCards.size());
        assertEquals(card1, actualCards.get(0));
        assertEquals(card2, actualCards.get(1));
    }
}
