package com.bartoszbalukiewicz.appsensor.event.publisher;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import com.bartoszbalukiewicz.security.SecurityUtils;
import org.owasp.appsensor.core.IPAddress;
import org.owasp.appsensor.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Bartek on 11.10.2016.
 */
@Component
public class AppSensorDetectionPointEventPublisherImpl implements AppSensorDetectionPointEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private transient IPAddress locator;

    @Override
    public void publishDetectionPointEvent(AppSensorDetectionPointEvent event, Authentication authentication) {
        event.setAppSensorUser(createAppSensorUser(authentication));
        eventPublisher.publishEvent(event);
    }

    @Override
    public void publishDetectionPointEvent(AppSensorDetectionPointEvent event) {
        event.setAppSensorUser(createAppSensorUser(SecurityUtils.getAuthentiaction()));
        eventPublisher.publishEvent(event);

    }

    @Override
    public void publishDetectionPointEventWithIP(AppSensorDetectionPointEvent event, Authentication authentication) {
        event.setAppSensorUser(createAppSensorUserWithIP(authentication));
        eventPublisher.publishEvent(event);
    }

    @Override
    public void publishDetectionPointEventWithSessionID(AppSensorDetectionPointEvent event, Authentication authentication) {
        event.setAppSensorUser(createAppSensorUserWithSessionId(authentication));
        eventPublisher.publishEvent(event);
    }

    private User createAppSensorUser(Authentication authentication) {
        return new User(getUserName(authentication), getUserIp(authentication));
    }

    private User createAppSensorUserWithIP(Authentication authentication) {
        IPAddress ipAddress = getUserIp(authentication);
        return new User(ipAddress.toString(), ipAddress);
    }

    private User createAppSensorUserWithSessionId(Authentication authentication) {
        return new User(getSessionid(authentication), getUserIp(authentication));
    }

    private IPAddress getUserIp(Authentication authentication) {
   /*     if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            return null;
        }*/

        // retrieve IP address for failure
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        if(remoteAddress == null) {
            return null;
        }

        return locator.fromString(remoteAddress);
    }

    private String getUserName(Authentication authentication) {
        String userName = authentication.getName();

        // overwrite if we can be more specific
        if (authentication instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)authentication;

            userName = userDetails.getUsername();
        }

        return userName;
    }

    private String getSessionid(Authentication authentication) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String sessionId = details.getSessionId();

        return sessionId != null ? sessionId : "";
    }
}
