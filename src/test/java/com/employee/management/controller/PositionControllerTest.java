package com.employee.management.controller;

import com.employee.management.model.PositionRequest;
import com.employee.management.model.PositionResponse;
import com.employee.management.service.PositionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PositionService positionService;

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void createPosition() throws Exception {
        PositionRequest request = new PositionRequest();
        request.setName("Test Position");

        PositionResponse expectedResponse = new PositionResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Test Position");

        Mockito.when(positionService.createPosition(request)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee-management/positions")
                        .content("{ \"name\": \"Test Position\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getAllPositions() throws Exception {
        PositionResponse position1 = new PositionResponse(1L, "Position 1",500,1L);
        PositionResponse position2 = new PositionResponse(2L, "Position 2",500,1L);
        Mockito.when(positionService.getAllPositions()).thenReturn(Arrays.asList(position1, position2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/positions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(position1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(position1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(position2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(position2.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getPositionById() throws Exception {
        Long positionId = 1L;
        PositionResponse expectedResponse = new PositionResponse();
        expectedResponse.setId(positionId);
        expectedResponse.setName("Test Position");

        Mockito.when(positionService.getPositionById(positionId)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee-management/positions/{id}", positionId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedResponse.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void editPosition() throws Exception {
        Long positionId = 1L;
        PositionRequest positionRequest = new PositionRequest();
        positionRequest.setName("Updated Position");

        PositionResponse updatedPosition = new PositionResponse();
        updatedPosition.setId(positionId);
        updatedPosition.setName(positionRequest.getName());

        Mockito.when(positionService.editPosition(positionId, positionRequest)).thenReturn(updatedPosition);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee-management/positions/{id}", positionId)
                        .content("{ \"name\": \"Updated Position\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedPosition.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedPosition.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void deletePosition() throws Exception {
        Long positionId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employee-management/positions/{id}", positionId))
                .andExpect(status().isOk());

        Mockito.verify(positionService, Mockito.times(1)).deletePosition(positionId);
    }
}
