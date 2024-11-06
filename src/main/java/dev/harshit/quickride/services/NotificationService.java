package dev.harshit.quickride.services;

import dev.harshit.quickride.models.Notification;
import dev.harshit.quickride.models.NotificationType;
import dev.harshit.quickride.models.UserType;
import dev.harshit.quickride.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Long userId, UserType userType,
                                 String message, NotificationType notificationType) {

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setTimeStamp(new Date());
        notification.setNotificationType(notificationType);
        notification.setUserType(userType);

        notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications(Long userId, UserType userType) {

        return notificationRepository.findByUserIdAndUserTypeAndIsReadFalse(userId, userType);
    }

    public void markNotificationAsRead(List<Notification> notifications) {

        notifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(notifications);
    }
}
