package dev.harshit.quickride.services;

import dev.harshit.quickride.dtos.RegisterVehicleRequestDto;
import dev.harshit.quickride.exceptions.DriverNotFoundException;
import dev.harshit.quickride.models.Driver;
import dev.harshit.quickride.models.Vehicle;
import dev.harshit.quickride.repositories.DriverRepository;
import dev.harshit.quickride.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;
    private DriverRepository driverRepository;

    public VehicleService(VehicleRepository vehicleRepository,
                          DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    public Vehicle saveVehicle(RegisterVehicleRequestDto requestDto) throws DriverNotFoundException {

        Optional<Driver> driver = driverRepository.findById(requestDto.getDriverId());
        if (driver.isEmpty()) {
            throw new DriverNotFoundException("Driver not found");
        }

        Driver driverObj = driver.get();

        Vehicle vehicle = new Vehicle();
        vehicle.setName(requestDto.getName());
        vehicle.setNumber(requestDto.getNumber());
        vehicle.setVehicleType(requestDto.getVehicleType());
        vehicle.setSeatCount(requestDto.getSeatCount());
        vehicle.setDriver(driverObj);

        return vehicleRepository.save(vehicle);
    }
}
