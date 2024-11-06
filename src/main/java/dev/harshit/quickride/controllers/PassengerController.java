package dev.harshit.quickride.controllers;

import dev.harshit.quickride.dtos.RegisterPassengerRequestDto;
import dev.harshit.quickride.dtos.RegisterPassengerResponseDto;
import dev.harshit.quickride.services.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<RegisterPassengerResponseDto> registerPassenger(
            @RequestBody RegisterPassengerRequestDto requestDto) {

        RegisterPassengerResponseDto savedPassenger = passengerService.savePassenger(requestDto);
        return ResponseEntity.ok(savedPassenger);
    }
}
