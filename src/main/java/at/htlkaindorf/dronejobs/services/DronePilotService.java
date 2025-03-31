package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.dto.DronePilotDto;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.entities.Specialty;
import at.htlkaindorf.dronejobs.repositories.DronePilotRepository;
import at.htlkaindorf.dronejobs.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        dronePilot.setName(pilot.getName());
        dronePilot.setLocation(pilot.getLocation());
        dronePilot.setAboutMe(pilot.getAboutMe());
        dronePilot.setRating(pilot.getRating());

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

}
