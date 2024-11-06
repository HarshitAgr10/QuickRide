package dev.harshit.quickride.controllers;

import dev.harshit.quickride.dtos.RegisterVehicleRequestDto;
import dev.harshit.quickride.exceptions.DriverNotFoundException;
import dev.harshit.quickride.models.Vehicle;
import dev.harshit.quickride.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody RegisterVehicleRequestDto requestDto) throws DriverNotFoundException {

        try {
            Vehicle savedVehicle = vehicleService.saveVehicle(requestDto);
            return ResponseEntity.ok(savedVehicle);
        } catch (DriverNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
