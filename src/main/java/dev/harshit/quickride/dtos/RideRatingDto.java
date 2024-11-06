package dev.harshit.quickride.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRatingDto {

    private Long rideId;

    private Double driverRating;

    private String driverFeedback;

    private Double passengerRating;

    private String passengerFeedback;
}
