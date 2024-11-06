package dev.harshit.quickride.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RideHistoryResponseDto {

    private List<RideDto> rides;
}
