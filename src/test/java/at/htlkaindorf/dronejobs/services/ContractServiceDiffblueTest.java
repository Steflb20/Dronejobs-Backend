package at.htlkaindorf.dronejobs.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import at.htlkaindorf.dronejobs.entities.Contract;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.repositories.ContractRepository;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ContractService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ContractServiceDiffblueTest {
    @MockBean
    private ContractRepository contractRepository;

    @Autowired
    private ContractService contractService;

    /**
     * Test {@link ContractService#saveContract(Contract)}.
     * <p>
     * Method under test: {@link ContractService#saveContract(Contract)}
     */
    @Test
    @DisplayName("Test saveContract(Contract)")
    void testSaveContract() {
        // Arrange
        DronePilot dronePilot = new DronePilot();
        dronePilot.setAboutMe("About Me");
        dronePilot.setFirstname("Jane");
        dronePilot.setId(1);
        dronePilot.setLastname("Doe");

        Contract contract = new Contract();
        contract.setDronePilot(dronePilot);
        contract.setFromDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        contract.setId(1);
        contract.setToDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(contractRepository.save(Mockito.<Contract>any())).thenReturn(contract);

        DronePilot dronePilot2 = new DronePilot();
        dronePilot2.setAboutMe("About Me");
        dronePilot2.setFirstname("Jane");
        dronePilot2.setId(1);
        dronePilot2.setLastname("Doe");

        Contract contract2 = new Contract();
        contract2.setDronePilot(dronePilot2);
        contract2.setFromDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        contract2.setId(1);
        contract2.setToDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        // Act
        Contract actualSaveContractResult = contractService.saveContract(contract2);

        // Assert
        verify(contractRepository).save(isA(Contract.class));
        assertSame(contract, actualSaveContractResult);
    }
}
