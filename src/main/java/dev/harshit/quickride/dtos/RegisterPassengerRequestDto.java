package dev.harshit.quickride.dtos;

import dev.harshit.quickride.models.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPassengerRequestDto {

    private String name;

    private String email;

    private String password;

    private String gender;

    private double rating;

    private UserType userType;
}
