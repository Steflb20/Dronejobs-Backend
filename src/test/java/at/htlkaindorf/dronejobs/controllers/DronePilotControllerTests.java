package at.htlkaindorf.dronejobs.controllers;

import at.htlkaindorf.dronejobs.dto.DronePilotDto;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.services.DronePilotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    /*
    @Test
    @DisplayName("should return all drones on /all route")
    public void testAllRoute() throws Exception {
        List<DronePilot> mockDronePilots = new ArrayList<>();
        DronePilot firstPilot = new DronePilot();
        firstPilot.setName("Max Mustermann");
        firstPilot.setAboutMe("I love drones :)");

        mockDronePilots.add(firstPilot);

        Mockito.when(dronePilotService.getAllDronePilots()).thenReturn(mockDronePilots);

        // viele testdaten -> viele verschiedene tests
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/dronepilot/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname").value("Max"))
                .andExpect(jsonPath("$[0].lastname").value("Mustermann"))
                .andExpect(jsonPath("$[0].aboutMe").value("I love drones :)"));
    }

    @Test
    @DisplayName("should save the dronepilot using the /save route")
    public void testSaveRoute() throws Exception {
        DronePilot pilot = new DronePilot();
        pilot.setName("Max Mustermann");
        pilot.setAboutMe("Heyy ;)");

        String json = new ObjectMapper().writeValueAsString(pilot);

        pilot.setId(1);

        Mockito.when(this.dronePilotService.saveDronePilot(Mockito.any(DronePilotDto.class))).thenReturn(pilot);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/dronepilot/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstname").value("Max"))
                .andExpect(jsonPath("$.lastname").value("Mustermann"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.aboutMe").value("Heyy ;)"));
    }*/
}
