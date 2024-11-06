package dev.harshit.quickride.controllers;

import dev.harshit.quickride.dtos.RideRatingDto;
import dev.harshit.quickride.exceptions.RideNotFoundException;
import dev.harshit.quickride.services.RideRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rides")
public class RideRatingController {

    private RideRatingService rideRatingService;

    public RideRatingController(RideRatingService rideRatingService) {
        this.rideRatingService = rideRatingService;
    }

    @PostMapping("/rate")
    public ResponseEntity<String> rateRide(@RequestBody RideRatingDto rideRatingDto)
            throws RideNotFoundException {

        rideRatingService.rateRide(rideRatingDto);
        return ResponseEntity.ok("Ride rated successfully");
    }
}
