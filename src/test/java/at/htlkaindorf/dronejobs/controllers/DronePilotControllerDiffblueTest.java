package at.htlkaindorf.dronejobs.controllers;

import static org.mockito.Mockito.when;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.services.DronePilotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DronePilotController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DronePilotControllerDiffblueTest {
    @Autowired
    private DronePilotController dronePilotController;

    @MockBean
    private DronePilotService dronePilotService;

    /**
     * Method under test: {@link DronePilotController#getAllDronePilots()}
     */
    @Test
    void testGetAllDronePilots() throws Exception {
        // Arrange
        when(dronePilotService.getAllDronePilots()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/dronepilot/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dronePilotController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link DronePilotController#saveDronePilot(DronePilot)}
     */
    @Test
    void testSaveDronePilot() throws Exception {
        // Arrange
        DronePilot dronePilot = new DronePilot();
        dronePilot.setAboutMe("About Me");
        dronePilot.setFirstname("Jane");
        dronePilot.setId(1);
        dronePilot.setLastname("Doe");
        when(dronePilotService.saveDronePilot(Mockito.<DronePilot>any())).thenReturn(dronePilot);

        DronePilot dronePilot2 = new DronePilot();
        dronePilot2.setAboutMe("About Me");
        dronePilot2.setFirstname("Jane");
        dronePilot2.setId(1);
        dronePilot2.setLastname("Doe");
        String content = (new ObjectMapper()).writeValueAsString(dronePilot2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/dronepilot/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dronePilotController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"firstname\":\"Jane\",\"lastname\":\"Doe\",\"aboutMe\":\"About Me\"}"));
    }
}
