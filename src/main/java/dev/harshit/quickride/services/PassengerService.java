package dev.harshit.quickride.services;

import dev.harshit.quickride.dtos.RegisterPassengerRequestDto;
import dev.harshit.quickride.dtos.RegisterPassengerResponseDto;
import dev.harshit.quickride.models.Passenger;
import dev.harshit.quickride.models.UserType;
import dev.harshit.quickride.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    private PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public RegisterPassengerResponseDto savePassenger(RegisterPassengerRequestDto requestDto) {

        Passenger passenger = new Passenger();
        passenger.setName(requestDto.getName());
        passenger.setEmail(requestDto.getEmail());
        passenger.setPassword(requestDto.getPassword());
        passenger.setGender(requestDto.getGender());
        passenger.setRating(requestDto.getRating());
        passenger.setUserType(UserType.PASSENGER);

        Passenger savedPassenger = passengerRepository.save(passenger);

        RegisterPassengerResponseDto responseDto = new RegisterPassengerResponseDto();
        responseDto.setId(savedPassenger.getId());
        responseDto.setName(savedPassenger.getName());
        responseDto.setEmail(savedPassenger.getEmail());
        responseDto.setGender(savedPassenger.getGender());
        responseDto.setRating(savedPassenger.getRating());
        responseDto.setUserType(savedPassenger.getUserType());

        return responseDto;
    }
}
