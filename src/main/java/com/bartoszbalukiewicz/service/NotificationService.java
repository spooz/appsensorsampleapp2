package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.model.Notification;
import com.bartoszbalukiewicz.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bartek on 06.12.2016.
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void add(String source, String ipAddress) {
        Notification notification = new Notification();
        notification.setSource(source);
        notification.setIpAddress(ipAddress);
        notificationRepository.save(notification);
    }
}
