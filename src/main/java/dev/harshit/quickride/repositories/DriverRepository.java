package dev.harshit.quickride.repositories;

import dev.harshit.quickride.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findById(Long driverId);

    Driver save(Driver driver);
}
