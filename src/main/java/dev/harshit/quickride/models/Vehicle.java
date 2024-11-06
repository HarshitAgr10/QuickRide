package dev.harshit.quickride.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "vehicles")
public class Vehicle extends BaseModel {

    private String name;

    private String number;

//    private Driver ownerName;

    @Enumerated(EnumType.ORDINAL)
    private VehicleType vehicleType;

    private int seatCount;

    @ManyToOne
    private Driver driver;
}
