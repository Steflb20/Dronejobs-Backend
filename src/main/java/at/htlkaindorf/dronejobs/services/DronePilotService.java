package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.repositories.DronePilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DronePilotService {
    private DronePilotRepository dronePilotRepository;

    @Autowired
    public DronePilotService(
            DronePilotRepository dronePilotRepository
    ) {
        this.dronePilotRepository = dronePilotRepository;
    }


    /**
     * Method used to save a pilot to the database
     * @param pilot The pilot
     */
    public DronePilot saveDronePilot(DronePilot pilot) {
        return this.dronePilotRepository.save(pilot);
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
        return this.dronePilotRepository.deleteDronePilotById(id);
    }
}
