package at.htlkaindorf.dronejobs.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
public class ContractDTO {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    private int dronePilotId;
}
