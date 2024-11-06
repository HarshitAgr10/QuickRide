package dev.harshit.quickride.dtos;

import dev.harshit.quickride.models.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RideDto {

    private Long rideId;

    private String source;;

    private String destination;

//    private int amount;

    private int availableSeatCounts;;

    private Long vehicleId;

    private String vehicleName;

    private VehicleType vehicleType;

    private Long driverId;

    private String driverName;

    private Date rideTimestamp;

    private double fare;
}
