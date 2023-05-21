package com.asi1.GameCard.user.controller;

import com.asi1.GameCard.user.model.User;
import com.asi1.GameCard.user.service.UserService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setId(1L);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = Arrays.asList(testUser);
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(Mockito.any(User.class))).thenReturn(testUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(testUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(anyLong(), Mockito.any(User.class))).thenReturn(testUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}", 1L)
                .content(objectMapper.writeValueAsString(testUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUser() throws Exception {
        when(userService.findUserById(anyLong())).thenReturn(java.util.Optional.ofNullable(testUser));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
