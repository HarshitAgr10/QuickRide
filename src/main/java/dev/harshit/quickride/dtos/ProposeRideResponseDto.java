package dev.harshit.quickride.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposeRideResponseDto {

    private Long rideId;

    private String source;

    private String destination;

//    private int amount;

    private int availableSeatCounts;

    private Long driverId;

    private Long vehicleId;

    private double fare;   // Calculated fare for the ride
}
