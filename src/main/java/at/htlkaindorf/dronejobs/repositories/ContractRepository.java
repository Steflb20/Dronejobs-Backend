package at.htlkaindorf.dronejobs.repositories;

import at.htlkaindorf.dronejobs.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
