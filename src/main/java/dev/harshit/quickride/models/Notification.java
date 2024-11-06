package dev.harshit.quickride.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Notification extends BaseModel {

    private String message;

    private Date timeStamp;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private Long userId;   // Stores the ID of the user (Driver or Passenger)

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean isRead = false;
}
