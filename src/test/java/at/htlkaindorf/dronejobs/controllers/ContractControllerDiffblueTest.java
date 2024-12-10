package at.htlkaindorf.dronejobs.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import at.htlkaindorf.dronejobs.entities.Contract;
import at.htlkaindorf.dronejobs.entities.ContractDTO;
import at.htlkaindorf.dronejobs.entities.DronePilot;
import at.htlkaindorf.dronejobs.repositories.ContractRepository;
import at.htlkaindorf.dronejobs.repositories.DronePilotRepository;
import at.htlkaindorf.dronejobs.services.ContractService;
import at.htlkaindorf.dronejobs.services.DronePilotService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ContractController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ContractControllerDiffblueTest {
    @Autowired
    private ContractController contractController;

    @MockBean
    private ContractService contractService;

    @MockBean
    private DronePilotService dronePilotService;

    /**
     * Test {@link ContractController#createContract(ContractDTO)}.
     * <p>
     * Method under test: {@link ContractController#createContract(ContractDTO)}
     */
    @Test
    @DisplayName("Test createContract(ContractDTO)")
    void testCreateContract() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        ContractService contractService = mock(ContractService.class);
        when(contractService.saveContract(Mockito.<Contract>any())).thenReturn(contract);

        DronePilot dronePilot2 = new DronePilot();
        dronePilot2.setAboutMe("About Me");
        dronePilot2.setFirstname("Jane");
        dronePilot2.setId(1);
        dronePilot2.setLastname("Doe");
        DronePilotRepository dronePilotRepository = mock(DronePilotRepository.class);
        when(dronePilotRepository.getDronePilotById(anyInt())).thenReturn(dronePilot2);
        ContractController contractController = new ContractController(contractService,
                new DronePilotService(dronePilotRepository));

        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setDronePilotId(1);
        contractDTO.setFromDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        contractDTO.setToDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        // Act
        ResponseEntity<Contract> actualCreateContractResult = contractController.createContract(contractDTO);

        // Assert
        verify(dronePilotRepository, atLeast(1)).getDronePilotById(eq(1));
        verify(contractService).saveContract(isA(Contract.class));
        HttpStatusCode statusCode = actualCreateContractResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(201, actualCreateContractResult.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, statusCode);
        assertTrue(actualCreateContractResult.hasBody());
        assertTrue(actualCreateContractResult.getHeaders().isEmpty());
        assertSame(contract, actualCreateContractResult.getBody());
    }

    /**
     * Test {@link ContractController#createContract(ContractDTO)}.
     * <p>
     * Method under test: {@link ContractController#createContract(ContractDTO)}
     */
    @Test
    @DisplayName("Test createContract(ContractDTO)")
    @Disabled("TODO: Complete this test")
    void testCreateContract2() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: at.htlkaindorf.dronejobs.entities.ContractDTO["fromDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1330)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:770)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:183)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:502)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:341)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4799)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:4040)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setDronePilotId(1);
        contractDTO.setFromDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        contractDTO.setToDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        String content = (new ObjectMapper()).writeValueAsString(contractDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/contract/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(contractController).build().perform(requestBuilder);
    }

    /**
     * Test {@link ContractController#createContract(ContractDTO)}.
     * <ul>
     *   <li>Given {@link ContractRepository} {@link CrudRepository#save(Object)}
     * return {@link Contract} (default constructor).</li>
     *   <li>Then calls {@link CrudRepository#save(Object)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ContractController#createContract(ContractDTO)}
     */
    @Test
    @DisplayName("Test createContract(ContractDTO); given ContractRepository save(Object) return Contract (default constructor); then calls save(Object)")
    void testCreateContract_givenContractRepositorySaveReturnContract_thenCallsSave() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        ContractRepository contractRepository = mock(ContractRepository.class);
        when(contractRepository.save(Mockito.<Contract>any())).thenReturn(contract);
        ContractService contractService = new ContractService(contractRepository);

        DronePilot dronePilot2 = new DronePilot();
        dronePilot2.setAboutMe("About Me");
        dronePilot2.setFirstname("Jane");
        dronePilot2.setId(1);
        dronePilot2.setLastname("Doe");
        DronePilotRepository dronePilotRepository = mock(DronePilotRepository.class);
        when(dronePilotRepository.getDronePilotById(anyInt())).thenReturn(dronePilot2);
        ContractController contractController = new ContractController(contractService,
                new DronePilotService(dronePilotRepository));

        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setDronePilotId(1);
        contractDTO.setFromDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        contractDTO.setToDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        // Act
        ResponseEntity<Contract> actualCreateContractResult = contractController.createContract(contractDTO);

        // Assert
        verify(dronePilotRepository, atLeast(1)).getDronePilotById(eq(1));
        verify(contractRepository).save(isA(Contract.class));
        HttpStatusCode statusCode = actualCreateContractResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(201, actualCreateContractResult.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, statusCode);
        assertTrue(actualCreateContractResult.hasBody());
        assertTrue(actualCreateContractResult.getHeaders().isEmpty());
        assertSame(contract, actualCreateContractResult.getBody());
    }

    /**
     * Test {@link ContractController#createContract(ContractDTO)}.
     * <ul>
     *   <li>Then calls {@link DronePilotService#findDronePilotById(int)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ContractController#createContract(ContractDTO)}
     */
    @Test
    @DisplayName("Test createContract(ContractDTO); then calls findDronePilotById(int)")
    void testCreateContract_thenCallsFindDronePilotById() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        ContractService contractService = mock(ContractService.class);
        when(contractService.saveContract(Mockito.<Contract>any())).thenReturn(contract);

        DronePilot dronePilot2 = new DronePilot();
        dronePilot2.setAboutMe("About Me");
        dronePilot2.setFirstname("Jane");
        dronePilot2.setId(1);
        dronePilot2.setLastname("Doe");
        DronePilotService dronePilotService = mock(DronePilotService.class);
        when(dronePilotService.findDronePilotById(anyInt())).thenReturn(dronePilot2);
        ContractController contractController = new ContractController(contractService, dronePilotService);

        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setDronePilotId(1);
        contractDTO.setFromDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        contractDTO.setToDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        // Act
        ResponseEntity<Contract> actualCreateContractResult = contractController.createContract(contractDTO);

        // Assert
        verify(contractService).saveContract(isA(Contract.class));
        verify(dronePilotService, atLeast(1)).findDronePilotById(eq(1));
        HttpStatusCode statusCode = actualCreateContractResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(201, actualCreateContractResult.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, statusCode);
        assertTrue(actualCreateContractResult.hasBody());
        assertTrue(actualCreateContractResult.getHeaders().isEmpty());
        assertSame(contract, actualCreateContractResult.getBody());
    }
}
