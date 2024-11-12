package at.htlkaindorf.dronejobs.controllers;

import at.htlkaindorf.dronejobs.entities.Contract;
import at.htlkaindorf.dronejobs.entities.ContractDTO;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.services.ContractService;
import at.htlkaindorf.dronejobs.services.DronePilotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private ContractService contractService;
    private DronePilotService dronePilotService;

    @Autowired
    public ContractController(ContractService contractService, DronePilotService dronePilotService) {
        this.contractService = contractService;
        this.dronePilotService = dronePilotService;
    }

    @PostMapping("save")
    public ResponseEntity<Contract> createContract(@RequestBody() ContractDTO contractDTO) {
        // Both dates in ISO-format.
        Contract contract = new Contract();

        contract.setToDate(contractDTO.getToDate());
        contract.setFromDate(contractDTO.getFromDate());

        if (contractDTO.getFromDate().isAfter(contractDTO.getToDate())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        DronePilot dronePilot =
                this.dronePilotService.findDronePilotById(contractDTO.getDronePilotId());
        if (dronePilot == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        contract.setDronePilot(
                this.dronePilotService.findDronePilotById(contractDTO.getDronePilotId()));

        return new ResponseEntity<>(
                this.contractService.saveContract(contract), HttpStatus.CREATED);
    }
}
