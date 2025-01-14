package at.htlkaindorf.dronejobs.repositories;

import at.htlkaindorf.dronejobs.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    Specialty getSpecialtyById(int id);
    Specialty getSpecialtyByName(String name);
    void deleteSpecialtyById(int id);
}
