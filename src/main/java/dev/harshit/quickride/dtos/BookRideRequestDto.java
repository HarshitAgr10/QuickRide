package dev.harshit.quickride.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRideRequestDto {

    private Long rideId;

    private Long passengerId;

    private int seatCount;
}
