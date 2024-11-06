package dev.harshit.quickride.controllers;

import dev.harshit.quickride.dtos.*;
import dev.harshit.quickride.exceptions.*;
import dev.harshit.quickride.services.RideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/propose")
    public ProposeRideResponseDto proposeRide(@RequestBody ProposeRideRequestDto requestDto)
            throws DriverNotFoundException, VehicleNotFoundException {

        return rideService.proposeRide(requestDto);
    }

    @PostMapping("/book")
    public BookRideResponseDto bookRide(@RequestBody BookRideRequestDto requestDto)
            throws InsufficientSeatsException, PassengerNotFoundException, RideNotFoundException {

        return rideService.bookRide(requestDto);
    }

    @PostMapping("/cancel")
    public CancelRideResponseDto cancelRide(@RequestBody CancelRideRequestDto requestDto)
            throws PassengerNotFoundException, RideNotFoundException {

        return rideService.cancelRide(requestDto);
    }

    @PostMapping("/history")
    public RideHistoryResponseDto rideHistory(@RequestBody RideHistoryRequestDto requestDto)
            throws DriverNotFoundException, PassengerNotFoundException {

        return rideService.rideHistory(requestDto);
    }

    @GetMapping("/search")
    public List<RideDto> searchRides(@RequestParam String source,
                                     @RequestParam String destination,
                                     @RequestParam int minAvailableSeats) {

        return rideService.searchRides(source, destination, minAvailableSeats);
    }
}
