package at.htlkaindorf.dronejobs.dto;

import lombok.Data;

@Data
public class DronePilotDto {

    private String aboutMe;
    private String firstname;
    private String lastname;
    private String location;
    private double stars;

    private String[] specialties;
}
