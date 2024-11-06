package dev.harshit.quickride.services;

import dev.harshit.quickride.dtos.RideRatingDto;
import dev.harshit.quickride.exceptions.RideNotFoundException;
import dev.harshit.quickride.models.Ride;
import dev.harshit.quickride.models.RideRating;
import dev.harshit.quickride.repositories.RideRatingRepository;
import dev.harshit.quickride.repositories.RideRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RideRatingService {

    private RideRatingRepository rideRatingRepository;
    private RideRepository rideRepository;

    public RideRatingService(RideRatingRepository rideRatingRepository,
                             RideRepository rideRepository) {

        this.rideRatingRepository = rideRatingRepository;
        this.rideRepository = rideRepository;
    }

    public void rateRide(RideRatingDto rideRatingDto) throws RideNotFoundException {

        Optional<Ride> findRide = rideRepository.findById(rideRatingDto.getRideId());
        if (findRide.isEmpty()) {
            throw new RideNotFoundException("Ride not found");
        }
        Ride findRideObj = findRide.get();

        RideRating rideRating = new RideRating();
        rideRating.setRide(findRideObj);
        rideRating.setDriverRating(rideRatingDto.getDriverRating());
        rideRating.setDriverFeedback(rideRatingDto.getDriverFeedback());
        rideRating.setPassengerRating(rideRatingDto.getPassengerRating());
        rideRating.setPassengerFeedback(rideRatingDto.getPassengerFeedback());

        rideRatingRepository.save(rideRating);
    }
}
