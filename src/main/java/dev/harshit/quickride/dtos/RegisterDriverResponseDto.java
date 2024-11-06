package dev.harshit.quickride.dtos;

import dev.harshit.quickride.models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDriverResponseDto {

    private Long id;

    private String name;

    private String email;

    private String gender;

    private double rating;

    private UserType userType;

}
