package at.htlkaindorf.dronejobs.controllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import at.htlkaindorf.dronejobs.entities.Specialty;
import at.htlkaindorf.dronejobs.services.SpecialtyService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
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

@ContextConfiguration(classes = {SpecialtyController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SpecialtyControllerDiffblueTest {
    @Autowired
    private SpecialtyController specialtyController;

    @MockBean
    private SpecialtyService specialtyService;

    /**
     * Test {@link SpecialtyController#findById(int)}.
     * <p>
     * Method under test: {@link SpecialtyController#findById(int)}
     */
    @Test
    @DisplayName("Test findById(int)")
    void testFindById() throws Exception {
        // Arrange
        Specialty specialty = new Specialty();
        specialty.setDronePilots(new ArrayList<>());
        specialty.setId(1);
        specialty.setName("Name");
        when(specialtyService.getSpecialtyById(anyInt())).thenReturn(specialty);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/specialty/findById/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(specialtyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"dronePilots\":[]}"));
    }

    /**
     * Test {@link SpecialtyController#save(Specialty)}.
     * <p>
     * Method under test: {@link SpecialtyController#save(Specialty)}
     */
    @Test
    @DisplayName("Test save(Specialty)")
    void testSave() throws Exception {
        // Arrange
        Specialty specialty = new Specialty();
        specialty.setDronePilots(new ArrayList<>());
        specialty.setId(1);
        specialty.setName("Name");
        when(specialtyService.saveSpecialty(Mockito.<Specialty>any())).thenReturn(specialty);

        Specialty specialty2 = new Specialty();
        specialty2.setDronePilots(new ArrayList<>());
        specialty2.setId(1);
        specialty2.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(specialty2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/specialty/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(specialtyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"dronePilots\":[]}"));
    }

    /**
     * Test {@link SpecialtyController#getAll()}.
     * <p>
     * Method under test: {@link SpecialtyController#getAll()}
     */
    @Test
    @DisplayName("Test getAll()")
    void testGetAll() throws Exception {
        // Arrange
        when(specialtyService.getAllSpecialty()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/specialty/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(specialtyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link SpecialtyController#findPilotsBySpecialtyId(int)}.
     * <p>
     * Method under test: {@link SpecialtyController#findPilotsBySpecialtyId(int)}
     */
    @Test
    @DisplayName("Test findPilotsBySpecialtyId(int)")
    void testFindPilotsBySpecialtyId() throws Exception {
        // Arrange
        Specialty specialty = new Specialty();
        specialty.setDronePilots(new ArrayList<>());
        specialty.setId(1);
        specialty.setName("Name");
        when(specialtyService.getSpecialtyById(anyInt())).thenReturn(specialty);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/specialty/findPilotsBySpecialtyId/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(specialtyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
