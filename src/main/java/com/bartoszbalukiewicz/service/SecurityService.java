package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.appsensor.event.events.access.AppSensorDetectionPointACE3Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import com.bartoszbalukiewicz.security.CurrentUser;
import com.bartoszbalukiewicz.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bartek on 12.11.2016.
 */
@Service("securityService")
public class SecurityService {

    @Autowired
    private AppSensorDetectionPointEventPublisher appSesnorDetectionPointEventPublisher;

    public boolean isAdmin() {
        CurrentUser currentUser = SecurityUtils.getAuthenticatedCurrentUser();
        if(currentUser == null || !currentUser.isAdmin()) {
            appSesnorDetectionPointEventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointACE3Event());
            return false;
        }

        return true;
    }

}
