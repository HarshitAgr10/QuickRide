package dev.harshit.quickride.services;

import dev.harshit.quickride.dtos.*;
import dev.harshit.quickride.exceptions.*;
import dev.harshit.quickride.models.*;
import dev.harshit.quickride.repositories.DriverRepository;
import dev.harshit.quickride.repositories.PassengerRepository;
import dev.harshit.quickride.repositories.RideRepository;
import dev.harshit.quickride.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.harshit.quickride.utilities.RideMapper.convertToRideDto;

@Service
public class RideService {

    private final FareCalculatorService fareCalculatorService;
    private PassengerRepository passengerRepository;
    private NotificationService notificationService;
    private RideRepository rideRepository;
    private DriverRepository driverRepository;
    private VehicleRepository vehicleRepository;

    public RideService(RideRepository rideRepository,
                       DriverRepository driverRepository,
                       VehicleRepository vehicleRepository, PassengerRepository passengerRepository, NotificationService notificationService, FareCalculatorService fareCalculatorService) {

        this.rideRepository = rideRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.passengerRepository = passengerRepository;
        this.notificationService = notificationService;
        this.fareCalculatorService = fareCalculatorService;
    }
    public ProposeRideResponseDto proposeRide(ProposeRideRequestDto requestDto) throws DriverNotFoundException, VehicleNotFoundException {

        // Fetch driver and vehicle
        Optional<Driver> driver = driverRepository.findById(requestDto.getDriverId());
        if (driver.isEmpty()) {
            throw new DriverNotFoundException("Driver not found");
        }
        Driver driverObj = driver.get();

        Optional<Vehicle> vehicle = vehicleRepository.findById(requestDto.getVehicleId());
        if (vehicle.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle not found");
        }
        Vehicle vehicleObj = vehicle.get();

        // Create and save the ride
        Ride ride = new Ride();
        ride.setSource(requestDto.getSource());
        ride.setDestination(requestDto.getDestination());
//        ride.setAmount(requestDto.getAmount());
        ride.setAvailableSeatCounts(requestDto.getAvailableSeatCounts());
        ride.setDriver(driverObj);
        ride.setVehicle(vehicleObj);

        double distanceInKm = requestDto.getDistanceInKm();
        double fare = fareCalculatorService.calculateFare(distanceInKm, vehicleObj.getVehicleType());
        ride.setFare(fare);

        rideRepository.save(ride);

        // Create ResponseDto
        ProposeRideResponseDto responseDto = new ProposeRideResponseDto();
        responseDto.setRideId(ride.getId());
        responseDto.setSource(ride.getSource());
        responseDto.setDestination(ride.getDestination());
//        responseDto.setAmount(ride.getAmount());
        responseDto.setAvailableSeatCounts(ride.getAvailableSeatCounts());
        responseDto.setDriverId(driverObj.getId());
        responseDto.setVehicleId(vehicleObj.getId());
        responseDto.setFare(fare);

        return responseDto;
    }

    public BookRideResponseDto bookRide(BookRideRequestDto requestDto)
            throws RideNotFoundException, PassengerNotFoundException, InsufficientSeatsException {

        Optional<Ride> ride = rideRepository.findById(requestDto.getRideId());
        if (ride.isEmpty()) {
            throw new RideNotFoundException("Ride not found");
        }
        Ride rideObj = ride.get();

        Optional<Passenger> passenger = passengerRepository.findById(requestDto.getPassengerId());
        if (passenger.isEmpty()) {
            throw new PassengerNotFoundException("Passenger not found");
        }
        Passenger passengerObj = passenger.get();

        if (rideObj.getAvailableSeatCounts() < requestDto.getSeatCount()) {
            throw new InsufficientSeatsException("Seats not available");
        }

        // Notify the driver about the booking
        String message = "The ride from " + rideObj.getSource() + " to " + rideObj.getDestination()
                + " has been booked by " + passengerObj.getName();
        notificationService.sendNotification(rideObj.getDriver().getId(), UserType.DRIVER,
                message, NotificationType.RIDE_BOOKED);


        // Notify the passenger
        String passengerMessage = "Your ride from " + rideObj.getSource() + " to " + rideObj.getDestination()
                + " has been booked with " + rideObj.getDriver().getName();
        notificationService.sendNotification(passengerObj.getId(), UserType.PASSENGER,
                passengerMessage, NotificationType.RIDE_BOOKED);


        rideObj.setAvailableSeatCounts(rideObj.getAvailableSeatCounts() - requestDto.getSeatCount());
        rideObj.getPassengers().add(passengerObj);
        rideRepository.save(rideObj);

        BookRideResponseDto responseDto = new BookRideResponseDto();
        responseDto.setRideId(rideObj.getId());
        responseDto.setPassengerId(passengerObj.getId());
        responseDto.setStatus("Booked");
        responseDto.setRemainingSeats(rideObj.getAvailableSeatCounts());

        return responseDto;
    }

    public CancelRideResponseDto cancelRide(CancelRideRequestDto requestDto)
            throws RideNotFoundException, PassengerNotFoundException {

        Optional<Ride> ride = rideRepository.findById(requestDto.getRideId());
        if (ride.isEmpty()) {
            throw new RideNotFoundException("Ride not found");
        }
        Ride rideObj = ride.get();

        Optional<Passenger> passenger = passengerRepository.findById(requestDto.getPassengerId());
        if (passenger.isEmpty()) {
            throw new PassengerNotFoundException("Passenger not found");
        }
        Passenger passengerObj = passenger.get();

        // Check if passenger is part of this ride
        if (!rideObj.getPassengers().contains(passengerObj)) {
            throw new PassengerNotFoundException("Passenger is not booked on this ride");
        }

        // Notify the driver
        String driverMessage = "Your ride from " + rideObj.getSource() + " to " +
                rideObj.getDestination() + " has been cancelled.";
        notificationService.sendNotification(rideObj.getDriver().getId(), UserType.DRIVER,
                driverMessage, NotificationType.RIDE_CANCELLED);

        for (Passenger p : rideObj.getPassengers()) {
            // Notify all passengers
            String passengerMessage = "The ride from " + rideObj.getSource() + " to " +
                    rideObj.getDestination() + " has been cancelled.";
            notificationService.sendNotification(passengerObj.getId(), UserType.PASSENGER,
                    passengerMessage, NotificationType.RIDE_CANCELLED);
        }

        // Remove passenger from ride and update seat count
        rideObj.getPassengers().remove(passengerObj);
        rideObj.setAvailableSeatCounts(rideObj.getAvailableSeatCounts() + requestDto.getSeatCount());
        rideRepository.save(rideObj);

        CancelRideResponseDto responseDto = new CancelRideResponseDto();
        responseDto.setRideId(rideObj.getId());
        responseDto.setPassengerId(passengerObj.getId());
        responseDto.setStatus("Cancelled");
        responseDto.setAvailableSeats(rideObj.getAvailableSeatCounts());

        return responseDto;
    }

    public RideHistoryResponseDto rideHistory(RideHistoryRequestDto requestDto)
            throws DriverNotFoundException, PassengerNotFoundException {

        List<RideDto> rideDto = new ArrayList<>();

        if (requestDto.getUserType() == UserType.DRIVER) {
            Optional<Driver> driver = driverRepository.findById(requestDto.getUserId());
            if (driver.isEmpty()) {
                throw new DriverNotFoundException("Driver not found");
            }
            Driver driverObj = driver.get();

            for (Ride ride : driverObj.getRides()) {
                rideDto.add(convertToRideDto(ride));
            }
        } else if (requestDto.getUserType() == UserType.PASSENGER) {
            Optional<Passenger> passenger = passengerRepository.findById(requestDto.getUserId());
            if (passenger.isEmpty()) {
                throw new PassengerNotFoundException("Passenger not found");
            }
            Passenger passengerObj = passenger.get();

            for (Ride ride : passengerObj.getRides()) {
                rideDto.add(convertToRideDto(ride));
            }
        }

        RideHistoryResponseDto responseDto = new RideHistoryResponseDto();
        responseDto.setRides(rideDto);

        return responseDto;
    }

    public List<RideDto> searchRides(String source, String destination, int minAvailableSeats) {

        List<Ride> rides = rideRepository.
                findBySourceAndDestinationAndAvailableSeatCountsGreaterThanEqual(
                        source, destination, minAvailableSeats);

        List<RideDto> rideDto = new ArrayList<>();
        for (Ride ride : rides) {
            rideDto.add(convertToRideDto(ride));
        }

        return rideDto;
    }
}
