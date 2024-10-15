package at.htlkaindorf.dronejobs.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.repositories.DronePilotRepository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DronePilotService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DronePilotServiceDiffblueTest {
    @MockBean
    private DronePilotRepository dronePilotRepository;

    @Autowired
    private DronePilotService dronePilotService;

    /**
     * Method under test: {@link DronePilotService#saveDronePilot(DronePilot)}
     */
    @Test
    void testSaveDronePilot() {
        // Arrange
        DronePilot dronePilot = new DronePilot();
        dronePilot.setAboutMe("About Me");
        dronePilot.setFirstname("Jane");
        dronePilot.setId(1);
        dronePilot.setLastname("Doe");
        when(dronePilotRepository.save(Mockito.<DronePilot>any())).thenReturn(dronePilot);

        DronePilot pilot = new DronePilot();
        pilot.setAboutMe("About Me");
        pilot.setFirstname("Jane");
        pilot.setId(1);
        pilot.setLastname("Doe");

        // Act
        DronePilot actualSaveDronePilotResult = dronePilotService.saveDronePilot(pilot);

        // Assert
        verify(dronePilotRepository).save(isA(DronePilot.class));
        assertSame(dronePilot, actualSaveDronePilotResult);
    }

    /**
     * Method under test: {@link DronePilotService#getAllDronePilots()}
     */
    @Test
    void testGetAllDronePilots() {
        // Arrange
        when(dronePilotRepository.findAll()).thenReturn(null);

        // Act
        List<DronePilot> actualAllDronePilots = dronePilotService.getAllDronePilots();

        // Assert
        verify(dronePilotRepository).findAll();
        assertNull(actualAllDronePilots);
    }

    /**
     * Method under test: {@link DronePilotService#deleteDronePilotById(int)}
     */
    @Test
    void testDeleteDronePilotById() {
        // Arrange
        DronePilot dronePilot = new DronePilot();
        dronePilot.setAboutMe("About Me");
        dronePilot.setFirstname("Jane");
        dronePilot.setId(1);
        dronePilot.setLastname("Doe");
        when(dronePilotRepository.getDronePilotById(anyInt())).thenReturn(dronePilot);
        doNothing().when(dronePilotRepository).delete(Mockito.<DronePilot>any());

        // Act
        DronePilot actualDeleteDronePilotByIdResult = dronePilotService.deleteDronePilotById(1);

        // Assert
        verify(dronePilotRepository).getDronePilotById(eq(1));
        verify(dronePilotRepository).delete(isA(DronePilot.class));
        assertSame(dronePilot, actualDeleteDronePilotByIdResult);
    }
}
