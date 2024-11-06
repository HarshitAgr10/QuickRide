package dev.harshit.quickride.services;

import dev.harshit.quickride.dtos.RegisterDriverRequestDto;
import dev.harshit.quickride.dtos.RegisterDriverResponseDto;
import dev.harshit.quickride.models.Driver;
import dev.harshit.quickride.models.UserType;
import dev.harshit.quickride.repositories.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public RegisterDriverResponseDto saveDriver(RegisterDriverRequestDto requestDto) {
        Driver driver = new Driver();
        driver.setName(requestDto.getName());
        driver.setEmail(requestDto.getEmail());
        driver.setPassword(requestDto.getPassword());
        driver.setGender(requestDto.getGender());
        driver.setRating(requestDto.getRating());
        driver.setUserType(UserType.DRIVER);

        Driver savedDriver = driverRepository.save(driver);

        RegisterDriverResponseDto responseDto = new RegisterDriverResponseDto();
        responseDto.setId(savedDriver.getId());
        responseDto.setName(savedDriver.getName());
        responseDto.setEmail(savedDriver.getEmail());
        responseDto.setGender(savedDriver.getGender());
        responseDto.setRating(savedDriver.getRating());
        responseDto.setUserType(savedDriver.getUserType());

        return responseDto;
    }
}
