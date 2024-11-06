package dev.harshit.quickride.repositories;

import dev.harshit.quickride.models.Ride;
import dev.harshit.quickride.models.RideRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRatingRepository extends JpaRepository<RideRating, Long> {

    List<RideRating> findByRide(Ride ride);
}
