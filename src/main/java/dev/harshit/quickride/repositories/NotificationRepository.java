package dev.harshit.quickride.repositories;

import dev.harshit.quickride.models.Notification;
import dev.harshit.quickride.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdAndUserTypeAndIsReadFalse(Long userId, UserType userType);
}
