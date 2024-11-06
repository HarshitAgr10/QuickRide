package dev.harshit.quickride.repositories;

import dev.harshit.quickride.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    @Override
    Optional<Ride> findById(Long rideId);

    List<Ride> findBySourceAndDestinationAndAvailableSeatCountsGreaterThanEqual(
            String source, String destination, int availableSeatCounts);
}
