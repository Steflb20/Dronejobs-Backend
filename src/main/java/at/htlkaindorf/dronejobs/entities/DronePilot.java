package at.htlkaindorf.dronejobs.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class DronePilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;
    private String aboutMe;
    private String location;

    @ManyToMany
    @JoinTable(name = "drone_pilot_specialties",
    joinColumns = {@JoinColumn(name = "drone_pilot_id")},
    inverseJoinColumns = {@JoinColumn(name = "specialties_id")})
    private Set<Specialty> specialties;
}
