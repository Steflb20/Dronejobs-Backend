package at.htlkaindorf.dronejobs.controllers;

import at.htlkaindorf.dronejobs.dto.DronePilotDto;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.services.DronePilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dronepilot")
public class DronePilotController {

    private DronePilotService dronePilotService;

    @Autowired
    public DronePilotController(
            DronePilotService dronePilotService
    ) {
        this.dronePilotService = dronePilotService;
    }

    @GetMapping("all")
    public List<DronePilot> getAllDronePilots() {
        return this.dronePilotService.getAllDronePilots();
    }

    @PostMapping("save")
    public DronePilot saveDronePilot(
            @RequestBody() DronePilotDto dronePilot
    ) {
        return this.dronePilotService.saveDronePilot(dronePilot);
    }

    @GetMapping("findById/{id}")
    public DronePilot findDronePilotById(@PathVariable int id) {
        return this.dronePilotService.findDronePilotById(id);
    }

    @DeleteMapping("deleteById/{id}")
    public DronePilot deleteDronePilotById(@PathVariable int id) {
        return this.dronePilotService.deleteDronePilotById(id);
    }

    @GetMapping("getFilteredDronePilots")
    public List<DronePilot> getFilteredDronePilots(@RequestParam String specialty, @RequestParam double minimumStars) {
        return this.dronePilotService.getFilteredDronePilots(specialty, minimumStars);
    }
}
