package com.govnext.backend.service;

import com.govnext.backend.entity.Notification;
import com.govnext.backend.entity.User;
import com.govnext.backend.exception.ResourceNotFoundException;
import com.govnext.backend.repository.NotificationRepository;
import com.govnext.backend.repository.UserRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository, 
                               UserRepository userRepository,
                               SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Notification sendNotification(String userEmail, String title, String message) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        Notification notification = new Notification(user, title, message);
        Notification saved = notificationRepository.save(notification);

        // Push real-time alert via WebSocket STOMP
        messagingTemplate.convertAndSendToUser(
                userEmail,
                "/queue/notifications",
                saved
        );

        return saved;
    }

    public List<Notification> getUserNotifications(String email) {
        return notificationRepository.findByUserEmailOrderByCreatedAtDesc(email);
    }

    public Notification markAsRead(Long notificationId) {
        if (notificationId == null) {
            throw new IllegalArgumentException("Notification ID cannot be null");
        }
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));

        notification.setRead(true);
        return notificationRepository.save(notification);
    }
}