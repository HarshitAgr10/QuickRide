package dev.harshit.quickride.utilities;

import dev.harshit.quickride.dtos.RideDto;
import dev.harshit.quickride.models.Ride;

public class RideMapper {

    public static RideDto convertToRideDto(Ride ride) {

        RideDto rideDto = new RideDto();
        rideDto.setRideId(ride.getId());
        rideDto.setSource(ride.getSource());
        rideDto.setDestination(ride.getDestination());
//        rideDto.setAmount(ride.getAmount());
        rideDto.setAvailableSeatCounts(ride.getAvailableSeatCounts());
        rideDto.setRideTimestamp(ride.getCreatedAt());
        rideDto.setFare(ride.getFare());

        if (ride.getVehicle() != null) {
            rideDto.setVehicleId(ride.getVehicle().getId());
            rideDto.setVehicleName(ride.getVehicle().getName());
            rideDto.setVehicleType(ride.getVehicle().getVehicleType());
        }

        if (ride.getDriver() != null) {
            rideDto.setDriverId(ride.getDriver().getId());
            rideDto.setDriverName(ride.getDriver().getName());
        }

        return rideDto;
    }
}
