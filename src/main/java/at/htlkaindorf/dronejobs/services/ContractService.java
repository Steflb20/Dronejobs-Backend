package at.htlkaindorf.dronejobs.services;

import at.htlkaindorf.dronejobs.entities.Contract;
import at.htlkaindorf.dronejobs.repositories.ContractRepository;
import at.htlkaindorf.dronejobs.repositories.DronePilotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContractService {

    private ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    /**
     * Method used to save a contract to the database
     * @param contract The contract
     */
    public Contract saveContract(Contract contract) {
        return this.contractRepository.save(contract);
    }
}
