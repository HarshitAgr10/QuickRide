package dev.harshit.quickride.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelRideResponseDto {

    private Long rideId;

    private Long passengerId;

    private String status;

    private int availableSeats;
}
