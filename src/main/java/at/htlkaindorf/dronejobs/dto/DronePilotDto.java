package at.htlkaindorf.dronejobs.dto;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
public class DronePilotDto {

    private String aboutMe;
    private String firstname;
    private String lastname;
    private String location;

    private String[] specialties;
}
