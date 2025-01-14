package at.htlkaindorf.dronejobs.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    @ManyToOne
    private DronePilot dronePilot;
}
