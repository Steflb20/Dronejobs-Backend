package at.htlkaindorf.dronejobs.repositories;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DronePilotRepository extends JpaRepository<DronePilot, Integer> {
    DronePilot getDronePilotById(int id);
}
