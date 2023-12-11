package com.employee.management.controller;

import com.employee.management.model.AuthenticationResponse;
import com.employee.management.model.LoginRequest;
import com.employee.management.model.UserRequest;
import com.employee.management.model.UserResponse;
import com.employee.management.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void saveUser() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("testpassword");

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setId(1L);
        expectedResponse.setUsername("testuser");

        Mockito.when(userService.saveUser(userRequest)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee-management/auth/register")
                        .content("{ \"username\": \"testuser\", \"password\": \"testpassword\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(expectedResponse.getUsername()));
    }

    @Test
    void loginUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        AuthenticationResponse authenticationResponse = new AuthenticationResponse("token");

        Mockito.when(userService.loginUser(loginRequest)).thenReturn(authenticationResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee-management/auth/login")
                        .content("{ \"username\": \"testuser\", \"password\": \"testpassword\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(authenticationResponse.getToken()));
    }


}
