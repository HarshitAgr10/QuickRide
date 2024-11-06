package dev.harshit.quickride.services;

import dev.harshit.quickride.models.VehicleType;
import org.springframework.stereotype.Service;

@Service
public class FareCalculatorService {

    public double calculateFare(double distanceInKm, VehicleType vehicleType) {

        final double base_fare = 50.0;

        double costPerKm = 0;

        switch (vehicleType) {
            case SUV:
                costPerKm = 15;
                break;

            case SEDAN:
                costPerKm = 12;
                break;

            case HATCHBACK:
                costPerKm = 10;
                break;
        }

        return base_fare + (costPerKm  * distanceInKm);
    }
}
