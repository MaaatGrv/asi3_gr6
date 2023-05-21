package com.asi1.GameCard.user.controller;

import com.asi1.GameCard.user.model.User;
import com.asi1.GameCard.user.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void getUser() {
        User user = new User();
        user.setId(1L);
        user.setLogin("test");

        when(userService.findUserById(user.getId())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUser(user.getId());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), user);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setLogin("test");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setLogin("updated_test");

        when(userService.findUserById(user.getId())).thenReturn(Optional.of(user));
        when(userService.updateUser(user.getId(), updatedUser)).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(user.getId(), updatedUser);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), updatedUser);
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setId(1L);
        user.setLogin("test");

        when(userService.findUserById(user.getId())).thenReturn(Optional.of(user));

        ResponseEntity<Void> response = userController.deleteUser(user.getId());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(userService).deleteUser(user.getId());
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setLogin("test1");

        User user2 = new User();
        user2.setId(2L);
        user2.setLogin("test2");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 2);
        assertEquals(response.getBody().get(0), user1);
        assertEquals(response.getBody().get(1), user2);
    }
}
