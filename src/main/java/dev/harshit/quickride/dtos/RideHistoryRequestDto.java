package dev.harshit.quickride.dtos;

import dev.harshit.quickride.models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideHistoryRequestDto {

    private Long userId;

    private UserType userType;
}
