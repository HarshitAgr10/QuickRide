package dev.harshit.quickride.controllers;

import dev.harshit.quickride.dtos.RegisterDriverRequestDto;
import dev.harshit.quickride.dtos.RegisterDriverResponseDto;
import dev.harshit.quickride.services.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<RegisterDriverResponseDto> addDriver(@RequestBody RegisterDriverRequestDto requestDto) {
        RegisterDriverResponseDto savedDriver = driverService.saveDriver(requestDto);

        return ResponseEntity.ok(savedDriver);
    }
}
