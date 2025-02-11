package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.dto.DronePilotDto;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.entities.Specialty;
import at.htlkaindorf.dronejobs.repositories.DronePilotRepository;
import at.htlkaindorf.dronejobs.repositories.SpecialtyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DronePilotService {
    private final SpecialtyRepository specialtyRepository;
    private DronePilotRepository dronePilotRepository;

    @Autowired
    public DronePilotService(
            DronePilotRepository dronePilotRepository,
            SpecialtyRepository specialtyRepository) {
        this.dronePilotRepository = dronePilotRepository;
        this.specialtyRepository = specialtyRepository;
    }


    /**
     * Method used to save a pilot to the database
     * @param pilot The pilot
     */
    public DronePilot saveDronePilot(DronePilotDto pilot) {

        DronePilot dronePilot = new DronePilot();

        dronePilot.setLastname(pilot.getLastname());
        dronePilot.setFirstname(pilot.getFirstname());
        dronePilot.setLocation(pilot.getLocation());
        dronePilot.setAboutMe(pilot.getAboutMe());
        dronePilot.setStars(pilot.getStars());

        Set<Specialty> specialties = new HashSet<>();

        for (int i = 0; i < pilot.getSpecialties().length; i++) {
            specialties.add(specialtyRepository.getSpecialtyByName(pilot.getSpecialties()[i]));
        }

        dronePilot.setSpecialties(specialties);

        return this.dronePilotRepository.save(dronePilot);
    }

    /**
     * Method to return all DronePilots from the database
     * @return all DronePilots
     */
    public List<DronePilot> getAllDronePilots() {
        return this.dronePilotRepository.findAll();
    }

    /**
     * Delete drone pilot by id from the database
     * @param id Id of dronePilot to delete
     * @return deleted drone pilot, if successful
     */
    public DronePilot deleteDronePilotById(int id) {
        DronePilot toDelete = new DronePilot();
        try {
            toDelete = this.dronePilotRepository.getDronePilotById(id);
            this.dronePilotRepository.delete(toDelete);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return toDelete;
    }

    /**
     * Method to return a DronePilot by id
     * @param id Id of the drone pilot to find
     * @return the drone pilot with the given id
     */
    public DronePilot findDronePilotById(int id) {
        return this.dronePilotRepository.getDronePilotById(id);
    }

    /**
     * Method to get the DronePilots by an amount of minimum stars and a specific specialty
     * @param specialty special category a pilot chooses
     * @param minimumStars review stars
     * @return the drone pilots with the given minimum stars and the specialty provided
     */
    public List<DronePilot> getFilteredDronePilots(String specialty, double minimumStars) {

        List<DronePilot> dronePilots = this.dronePilotRepository.findAll();
        List<DronePilot> filteredDronePilots = new ArrayList<>();

        dronePilots = dronePilots.stream().filter(d -> d.getStars() >= minimumStars).toList();

        dronePilots.forEach(d -> {
            d.getSpecialties().forEach(s -> {
                if (s.getName().equals(specialty)) {
                    filteredDronePilots.add(d);
                }
            });
        });

        return filteredDronePilots;
    }

    /*public void updateDronePilot(int id, DronePilotDto dto) {
        DronePilot dronePilot = this.dronePilotRepository.getDronePilotById(id);
        dronePilot.setLastname(dto.getLastname());
        dronePilot.setFirstname(dto.getFirstname());
        dronePilot.setLocation(dto.getLocation());
        dronePilot.setAboutMe(dto.getAboutMe());
        dronePilot.setStars(dto.getStars());

        Set<Specialty> specialties = new HashSet<>();

        for (int i = 0; i < dto.getSpecialties().length; i++) {
            specialties.add(specialtyRepository.getSpecialtyByName(dto.getSpecialties()[i]));
        }
        dronePilot.setSpecialties(specialties);
        this.dronePilotRepository.save(dronePilot);
    }*/

    @Transactional
    public DronePilot updateDronePilot(int id, DronePilotDto dronePilotDto) {
        return dronePilotRepository.findById(id).map(pilot -> {
            pilot.setFirstname(dronePilotDto.getFirstname());
            pilot.setLastname(dronePilotDto.getLastname());
            pilot.setAboutMe(dronePilotDto.getAboutMe());
            pilot.setLocation(dronePilotDto.getLocation());
            pilot.setStars(dronePilotDto.getStars());
            return dronePilotRepository.save(pilot);
        }).orElseThrow(() -> new EntityNotFoundException("DronePilot mit ID " + id + " nicht gefunden"));
    }

}
