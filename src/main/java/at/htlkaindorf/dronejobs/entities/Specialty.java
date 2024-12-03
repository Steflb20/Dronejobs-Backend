package at.htlkaindorf.dronejobs.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(name = "drone_pilot_specialties",
            joinColumns = {@JoinColumn(name = "specialties_id")},
            inverseJoinColumns = {@JoinColumn(name = "drone_pilot_id")})
    @ToString.Exclude
    private List<DronePilot> dronePilots;
}
