package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class DronePilotServiceTests {

    private DronePilotService service;
    private EntityManager entityManager;

    @Autowired
    public DronePilotServiceTests(
            DronePilotService service,
            EntityManager entityManager
    ) {
        this.service = service;
        this.entityManager = entityManager;
    }

    @Test()
    @DisplayName("it should store the values in the database properly")
    public void testDatabaseSave() {
        DronePilot dronePilot = new DronePilot();
        dronePilot.setFirstname("Max");
        dronePilot.setLastname("Mustermann");
        dronePilot.setAboutMe("I like drones :)");

        this.service.saveDronePilot(dronePilot);

        List<DronePilot> list = this.entityManager.createQuery("SELECT p FROM DronePilot p").getResultList();

        assertThat(list.size()).isEqualTo(1);
    }

    @Test()
    @DisplayName("it should return all values from the database")
    public void testSelectAll() {
        DronePilot firstDronePilot = new DronePilot();
        firstDronePilot.setFirstname("Max");
        firstDronePilot.setLastname("Mustermann");
        firstDronePilot.setAboutMe("I like drones :)");

        DronePilot secondDronePilot = new DronePilot();
        secondDronePilot.setFirstname("Manuela");
        secondDronePilot.setLastname("Musterfrau");
        secondDronePilot.setAboutMe("I love drones hehe");

        this.entityManager.persist(firstDronePilot);
        this.entityManager.persist(secondDronePilot);

        List<DronePilot> allPilots = this.service.getAllDronePilots();

        assertThat(allPilots.size()).isEqualTo(2);
    }
}