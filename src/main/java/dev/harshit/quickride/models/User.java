package dev.harshit.quickride.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User extends BaseModel {

    private String name;

    private String email;

    private String password;

    private String gender;

    private double rating;

    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
}



/**
 * 4 ways to represent inheritance in database
 * 1. Mapped Super Class
 * 2. Joined Table
 * 3. Table per class
 * 4. Single Table
 */