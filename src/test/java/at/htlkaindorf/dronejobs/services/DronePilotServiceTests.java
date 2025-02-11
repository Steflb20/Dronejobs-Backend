package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.dto.DronePilotDto;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.entities.Specialty;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test()
    @DisplayName("it should delete 1 pilot from the database")
    public void testDeletePilot() {
        DronePilot pilotToDelete = new DronePilot();
        pilotToDelete.setFirstname("Max");
        pilotToDelete.setLastname("Mustermann");
        pilotToDelete.setAboutMe("I dont want to be deleted :(");

        DronePilot pilotToDelete2 = new DronePilot();
        pilotToDelete2.setFirstname("Manuela");
        pilotToDelete2.setLastname("Musterfrau");
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
    }

    @Test
    @DisplayName("it should filter DronePilots by minimum stars and specialty")
    public void testGetFilteredDronePilots() {
        DronePilot pilot1 = new DronePilot();
        pilot1.setFirstname("Max");
        pilot1.setLastname("Mustermann");
        pilot1.setStars(4.5);
        Specialty specialty1 = new Specialty();
        specialty1.setName("Photography");
        pilot1.setSpecialties(Set.of(specialty1));

        DronePilot pilot2 = new DronePilot();
        pilot2.setFirstname("Manuela");
        pilot2.setLastname("Musterfrau");
        pilot2.setStars(3.0);
        Specialty specialty2 = new Specialty();
        specialty2.setName("Surveying");
        pilot2.setSpecialties(Set.of(specialty2));

        DronePilot pilot3 = new DronePilot();
        pilot3.setFirstname("John");
        pilot3.setLastname("Doe");
        pilot3.setStars(5.0);
        pilot3.setSpecialties(Set.of(specialty1));

        this.entityManager.persist(pilot1);
        this.entityManager.persist(pilot2);
        this.entityManager.persist(pilot3);

        List<DronePilot> allPilotsBefore = this.entityManager.createQuery("FROM DronePilot", DronePilot.class).getResultList();
        log.info("All Pilots Before Filtering:");
        allPilotsBefore.forEach(p -> log.info(p.toString()));

        List<DronePilot> filteredPilots = this.service.getFilteredDronePilots("Photography", 4.0);

        log.info("Filtered Pilots:");
        filteredPilots.forEach(p -> log.info(p.toString()));

        assertEquals(2, filteredPilots.size());
        //assertThat(filteredPilots).extracting(DronePilot::getFirstname).containsExactlyInAnyOrder("Max", "John");
        List<String> firstNames = filteredPilots.stream().map(DronePilot::getFirstname).collect(Collectors.toList());
        assertTrue(firstNames.containsAll(List.of("Max", "John")));
    }

    @Test
    @DisplayName("it should retrieve a DronePilot by ID")
    public void testGetDronePilotById() {
        DronePilot pilot = new DronePilot();
        pilot.setFirstname("Alice");
        pilot.setLastname("Wonderland");
        pilot.setAboutMe("Explorer of the skies");

        this.entityManager.persist(pilot);
        int id = pilot.getId();

        DronePilot foundPilot = this.service.findDronePilotById(id);

        assertThat(foundPilot).isNotNull();
        assertThat(foundPilot.getFirstname()).isEqualTo("Alice");
    }

    @Test
    @DisplayName("it should update a DronePilot's information")
    public void testUpdateDronePilot() {
        DronePilot pilot = new DronePilot();
        pilot.setFirstname("Bob");
        pilot.setLastname("Builder");
        pilot.setAboutMe("I build drone routes");

        this.entityManager.persist(pilot);
        int id = pilot.getId();

        DronePilotDto updateDto = new DronePilotDto();
        updateDto.setFirstname("Bobby");
        updateDto.setLastname("Fixer");
        updateDto.setAboutMe("I fix drone routes");

        this.service.updateDronePilot(id, updateDto);

        DronePilot updatedPilot = this.entityManager.find(DronePilot.class, id);

        assertThat(updatedPilot.getFirstname()).isEqualTo("Bobby");
        assertThat(updatedPilot.getLastname()).isEqualTo("Fixer");
        assertThat(updatedPilot.getAboutMe()).isEqualTo("I fix drone routes");
    }

    @Test
    @DisplayName("it should return null when deleting a non-existent DronePilot")
    public void testDeleteNonExistentPilot() {
        int fakeId = 999;
        DronePilot dronePilote = this.service.deleteDronePilotById(fakeId);
        assertThat(dronePilote).isNull();
    }
}