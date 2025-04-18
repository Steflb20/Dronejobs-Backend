package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.dto.DronePilotDto;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
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

    /*
    @Test()
    @DisplayName("it should store the values in the database properly")
    public void testDatabaseSave() {
        DronePilotDto dronePilot = new DronePilotDto();
        dronePilot.setFirstname("Max");
        dronePilot.setLastname("Mustermann");
        dronePilot.setAboutMe("I like drones :)");

        this.service.saveDronePilot(dronePilot);

        List<DronePilot> list = this.entityManager.createQuery("SELECT p FROM DronePilot p", DronePilot.class).getResultList();

        assertThat(list.size()).isEqualTo(1);
    }

    @Test()
    @DisplayName("it should return all values from the database")
    public void testSelectAll() {
        DronePilot firstDronePilot = new DronePilot();
        firstDronePilot.setName("Max Mustermann");
        firstDronePilot.setAboutMe("I like drones :)");

        DronePilot secondDronePilot = new DronePilot();
        secondDronePilot.setName("Manuela Musterfrau");
        secondDronePilot.setAboutMe("I love drones hehe");

        this.entityManager.persist(firstDronePilot);
        this.entityManager.persist(secondDronePilot);

        List<DronePilot> allPilots = this.service.getAllDronePilots();

        assertThat(allPilots.size()).isEqualTo(2);
    }

    @Test()
    @DisplayName("it should delete 1 pilot from the database")
    public void testDeletePilot() {
        DronePilot pilotToDelete = new DronePilot();
        pilotToDelete.setName("Max Mustermann");
        pilotToDelete.setAboutMe("I dont want to be deleted :(");

        DronePilot pilotToDelete2 = new DronePilot();
        pilotToDelete2.setName("Manuela Musterfrau");
        pilotToDelete2.setAboutMe("I am a good drone pilot!");

        this.entityManager.persist(pilotToDelete);
        this.entityManager.persist(pilotToDelete2);

        List<DronePilot> allPilotsBefore = this.entityManager.createQuery("FROM DronePilot", DronePilot.class).getResultList();
        int sizeBefore = allPilotsBefore.size();

        allPilotsBefore.forEach(p -> log.info(p.toString()));
        log.info("Deleted Pilot: {}", this.service.deleteDronePilotById(pilotToDelete.getId()));


        List<DronePilot> allPilotsAfter = this.entityManager.createQuery("FROM DronePilot", DronePilot.class).getResultList();
        int sizeAfter = allPilotsAfter.size();

        assertThat(sizeAfter).isEqualTo(sizeBefore - 1);
    }*/
}
