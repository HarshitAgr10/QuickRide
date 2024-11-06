package dev.harshit.quickride.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RideRating extends BaseModel {

    @ManyToOne
    private Ride ride;

    private double driverRating;

    private String driverFeedback;

    private double passengerRating;

    private String passengerFeedback;
}
