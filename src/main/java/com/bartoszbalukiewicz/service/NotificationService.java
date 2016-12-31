package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.model.Notification;
import com.bartoszbalukiewicz.model.User;
import com.bartoszbalukiewicz.model.view.NotificationView;
import com.bartoszbalukiewicz.repository.NotificationRepository;
import com.bartoszbalukiewicz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.smsapi.Client;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Bartek on 06.12.2016.
 */
@Service

public class NotificationService {

    private Client smsApiClient;
    private SmsFactory smsApiFactory;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void add(String source, String ipAddress) {
        Notification notification = new Notification();
        notification.setSource(source);
        notification.setIpAddress(ipAddress);
        notificationRepository.save(notification);


        List<User> admins = userRepository.findByIsAdmin(Boolean.TRUE);
        for(User admin : admins) {
            if(admin.getNotificationsPhone() != null && !admin.getNotificationsPhone().isEmpty()) {
                sendSMS(admin.getNotificationsPhone(), source + " " + ipAddress);
            }
        }
    }

    @PreAuthorize("@securityService.isAdmin()")
    public List<NotificationView> findAll() {
        return notificationRepository.getAll();
    }

    @PostConstruct
    public void initClient() throws ClientException {
        smsApiClient = new Client("bartosz.balukiewicz@cryptomage.io");
        smsApiClient.setPasswordHash("166118d9fe52df1659e879365ce16b06");
        smsApiFactory = new SmsFactory(smsApiClient);
    }

    @Async
    private void sendSMS(String to, String message) {
        SMSSend action = smsApiFactory.actionSend(to, message);
        action.setSender("ECO");
        try {
            action.execute();
        } catch (SmsapiException e) {

        }
    }


}
