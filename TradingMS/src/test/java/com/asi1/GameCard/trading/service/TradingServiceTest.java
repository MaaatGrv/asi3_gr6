package com.asi1.GameCard.trading.service;

import com.asi1.GameCard.trading.model.Trading;
import com.asi1.GameCard.trading.dto.UserDto;
import com.asi1.GameCard.trading.dto.CardDto;
import com.asi1.GameCard.trading.repository.TradingRepository;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class TradingServiceTest {

        // @Mock
        // private TradingRepository tradingRepository;

        // @Mock
        // private RestTemplate restTemplate;

        // @InjectMocks
        // private TradingService tradingService;

        // @Test
        // public void testBuyCard() {
        // UserDto userDto = new UserDto(
        // 1L,
        // "login",
        // "pwd",
        // 200.0,
        // "lastName",
        // "surName",
        // "email",
        // new ArrayList<>(Arrays.asList(1L)));
        // userDto.setId(1L);
        // userDto.setAccount(200.0);

        // CardDto cardDto = new CardDto();
        // cardDto.setId(1L);
        // cardDto.setPrice(50);

        // HttpHeaders headers = new HttpHeaders();
        // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // when(restTemplate.exchange(
        // "http://gateway/user/1",
        // HttpMethod.GET,
        // entity,
        // UserDto.class)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        // when(restTemplate.exchange(
        // "http://gateway/card/1",
        // HttpMethod.GET,
        // entity,
        // CardDto.class)).thenReturn(new ResponseEntity<>(cardDto, HttpStatus.OK));

        // // Display card et user
        // System.out.println("Card : " + cardDto);
        // System.out.println("User : " + userDto);

        // Trading result = tradingService.buyCard(1L, 1L);

        // assertNotNull(result);
        // assertEquals("buy", result.getType());
        // assertEquals(1L, result.getUserId());
        // assertEquals(1L, result.getCardId());
        // }

        // @Test
        // public void testSellCard() {
        // UserDto userDto = new UserDto(
        // 1L,
        // "login",
        // "pwd",
        // 200.0,
        // "lastName",
        // "surName",
        // "email",
        // new ArrayList<>(Arrays.asList(1L)));

        // CardDto cardDto = new CardDto();
        // cardDto.setId(1L);
        // cardDto.setPrice(50);
        // cardDto.setUserId(1L);

        // HttpHeaders headers = new HttpHeaders();
        // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // when(restTemplate.exchange(
        // "http://gateway/user/1",
        // HttpMethod.GET,
        // entity,
        // UserDto.class)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

        // when(restTemplate.exchange(
        // "http://gateway/card/1",
        // HttpMethod.GET,
        // entity,
        // CardDto.class)).thenReturn(new ResponseEntity<>(cardDto, HttpStatus.OK));

        // // Mock restTemplate.put() for userDto
        // doNothing().when(restTemplate).put(eq("http://gateway/user/" +
        // userDto.getId()), eq(userDto),
        // eq(UserDto.class));

        // // Mock restTemplate.put() for cardDto
        // doNothing().when(restTemplate).put(eq("http://gateway/card/" +
        // cardDto.getId()), eq(cardDto),
        // eq(CardDto.class));

        // Trading result = tradingService.sellCard(1L, 1L);

        // assertNotNull(result);
        // assertEquals("sell", result.getType());
        // assertEquals(1L, result.getUserId());
        // assertEquals(1L, result.getCardId());
        // }
}