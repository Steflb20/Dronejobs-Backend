package at.htlkaindorf.dronejobs.controller;

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
            @RequestBody() DronePilot dronePilot
    ) {
        return this.dronePilotService.saveDronePilot(dronePilot);
    }
}
