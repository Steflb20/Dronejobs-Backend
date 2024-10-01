package at.htlkaindorf.dronejobs.controllers;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.services.DronePilotService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class DronePilotControllerTests {

    @MockBean
    private DronePilotService dronePilotService;

    private MockMvc mockMvc;

    @Autowired
    public DronePilotControllerTests(
        MockMvc mockMvc
    ) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("should return all drones on /all route")
    public void testAllRoute() throws Exception {
        List<DronePilot> mockDronePilots = new ArrayList<>();
        DronePilot firstPilot = new DronePilot();
        firstPilot.setFirstname("Max");
        firstPilot.setLastname("Mustermann");
        firstPilot.setBio("I love drones :)");

        mockDronePilots.add(firstPilot);

        Mockito.when(dronePilotService.getAllDronePilots()).thenReturn(mockDronePilots);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/dronepilot/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(
                        MockMvcResultMatchers.content().string(
                                Matchers.containsString("")
                        )
                );
    }
}
