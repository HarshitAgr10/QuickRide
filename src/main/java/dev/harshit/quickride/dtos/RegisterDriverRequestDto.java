package dev.harshit.quickride.dtos;

import dev.harshit.quickride.models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDriverRequestDto {

    private String name;

    private String email;

    private String password;

    private String gender;

    private double rating;

    private UserType userType;
}
