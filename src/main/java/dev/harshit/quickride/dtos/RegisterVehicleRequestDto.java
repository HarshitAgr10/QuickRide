package dev.harshit.quickride.dtos;

import dev.harshit.quickride.models.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVehicleRequestDto {

    private String name;

    private String number;

    private VehicleType vehicleType;

    private int seatCount;

    private Long driverId;
}
