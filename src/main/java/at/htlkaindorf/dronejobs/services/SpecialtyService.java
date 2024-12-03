package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.entities.Specialty;
import at.htlkaindorf.dronejobs.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SpecialtyService {

    private SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }


    /**
     * Method to return all Specialties from the database
     * @return all Specialties
     */
    public List<Specialty> getAllSpecialty() {
        return specialtyRepository.findAll();
    }

    public Specialty saveSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public Specialty getSpecialtyById(int id) {
        return specialtyRepository.getSpecialtyById(id);
    }

    public void deleteSpecialty(int id) {
        specialtyRepository.deleteSpecialtyById(id);
    }
}
