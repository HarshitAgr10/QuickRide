package dev.harshit.quickride.controllers;

import dev.harshit.quickride.models.Notification;
import dev.harshit.quickride.models.UserType;
import dev.harshit.quickride.repositories.NotificationRepository;
import dev.harshit.quickride.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService, NotificationRepository notificationRepository) {
        this.notificationService = notificationService;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications(@RequestParam Long userId,
                                                     @RequestParam UserType userType) {

        return notificationService.getUnreadNotifications(userId, userType);
    }

    @PostMapping("/mark-read")
    public ResponseEntity<String> markNotificationsAsRead(@RequestBody List<Long> notificationIds) {

        List<Notification> notifications = notificationRepository.findAllById(notificationIds);
        notificationService.markNotificationAsRead(notifications);

        return ResponseEntity.ok("Notifications marked as read.");
    }
}
