package at.htlkaindorf.dronejobs.controllers;

import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.entities.Specialty;
import at.htlkaindorf.dronejobs.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specialty")
public class SpecialtyController {

    private SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping("all")
    public List<Specialty> getAll() {
        return this.specialtyService.getAllSpecialty();
    }

    @PostMapping("save")
    public Specialty save(@RequestBody() Specialty specialty) {
        return this.specialtyService.saveSpecialty(specialty);
    }

    @GetMapping("findById/{id}")
    public Specialty findById(@PathVariable int id) {
        return this.specialtyService.getSpecialtyById(id);
    }

    @GetMapping("findPilotsBySpecialtyId/{id}")
    public List<DronePilot> findPilotsBySpecialtyId(@PathVariable int id) {
        Specialty specialty = this.specialtyService.getSpecialtyById(id);

        if (specialty == null) {
            return null;
        }
        return specialty.getDronePilots();
    }
}
