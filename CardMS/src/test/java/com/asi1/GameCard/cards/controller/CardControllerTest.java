package com.asi1.GameCard.cards.controller;

import com.asi1.GameCard.cards.model.Card;
import com.asi1.GameCard.cards.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    // @Autowired
    // private MockMvc mockMvc;

    // @Autowired
    // private ObjectMapper objectMapper;

    // @MockBean
    // private CardService cardService;

    // private Card testCard;

    // @BeforeEach
    // public void setUp() {
    // testCard = new Card("TestCard", "A test card", "TestFamily", "TestAffinity",
    // "TestImgUrl", "TestSmallImgUrl", 10, 100, 50, 50, 100, 1L);
    // testCard.setId(1L);
    // }

    // @Test
    // public void testGetAllCards() throws Exception {
    // List<Card> cardList = Arrays.asList(testCard);
    // when(cardService.getAllCards()).thenReturn(cardList);

    // mockMvc.perform(MockMvcRequestBuilders.get("/cards")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
    // }

    // @Test
    // public void testGetCard() throws Exception {
    // when(cardService.getCard(anyLong())).thenReturn(testCard);

    // mockMvc.perform(MockMvcRequestBuilders.get("/card/{id}", 1L)
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
    // }

    // @Test
    // public void testCreateCard() throws Exception {
    // when(cardService.createCard(any(Card.class))).thenReturn(testCard);

    // mockMvc.perform(MockMvcRequestBuilders.post("/card")
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(objectMapper.writeValueAsString(testCard)))
    // .andExpect(status().isOk());
    // }

    // @Test
    // public void testUpdateCard() throws Exception {
    // Card updatedCard = new Card("UpdatedCard", "An updated card", "TestFamily",
    // "TestAffinity",
    // "TestImgUrl", "TestSmallImgUrl", 10, 100, 50, 50, 100, 1L);
    // updatedCard.setId(1L);

    // when(cardService.updateCard(anyLong(),
    // any(Card.class))).thenReturn(updatedCard);

    // mockMvc.perform(MockMvcRequestBuilders.put("/card/{id}", 1L)
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(objectMapper.writeValueAsString(updatedCard)))
    // .andExpect(status().isOk());
    // }

    // @Test
    // public void testDeleteCard() throws Exception {
    // Mockito.doNothing().when(cardService).deleteCard(anyLong());

    // mockMvc.perform(MockMvcRequestBuilders.delete("/card/{id}", 1L)
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
    // }

    // @Test
    // public void testGetCardsByUserId() throws Exception {
    // List<Card> cardList = Arrays.asList(testCard);
    // when(cardService.getCardsByUserId(anyLong())).thenReturn(cardList);

    // mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}/cards", 1L)
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
    // }

    // @Test
    // public void testGetCardsForSale() throws Exception {
    // List<Card> cardList = Arrays.asList(testCard);
    // when(cardService.getCardsForSale()).thenReturn(cardList);

    // mockMvc.perform(MockMvcRequestBuilders.get("/cards_to_sell")
    // .contentType(MediaType.APPLICATION_JSON))
    // .andExpect(status().isOk());
    // }

}
