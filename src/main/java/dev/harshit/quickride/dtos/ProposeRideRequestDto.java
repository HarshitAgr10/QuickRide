package dev.harshit.quickride.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposeRideRequestDto {

    private String source;

    private String destination;

//    private int amount;

    private int availableSeatCounts;

    private Long driverId;   // ID of the driver proposing the ride

    private Long vehicleId;  // ID of the vehicle used for the ride

    private double distanceInKm;   // Distance for fare calculation
}
