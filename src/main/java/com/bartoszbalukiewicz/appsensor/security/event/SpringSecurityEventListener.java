package com.bartoszbalukiewicz.appsensor.security.event;

import com.bartoszbalukiewicz.appsensor.event.events.auth.AppSensorDetectionPointAE3Event;
import com.bartoszbalukiewicz.appsensor.event.events.trend.AppSensorDetectionPointSTE2Event;
import com.bartoszbalukiewicz.appsensor.event.events.trend.AppSesnorDetectionPointSTE1Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by postgres on 2016-09-27.
 */
@Component
public class SpringSecurityEventListener  {

    @Autowired
    private AppSensorDetectionPointEventPublisher eventPublisher;

    @Async
    @EventListener
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        // process event
        //TODO TEST
        if (applicationEvent instanceof AbstractAuthenticationFailureEvent) {
            //TODO USER IP AS USERNAME
            AbstractAuthenticationFailureEvent event = (AbstractAuthenticationFailureEvent)applicationEvent;
            eventPublisher.publishDetectionPointEventWithIP(new AppSensorDetectionPointAE3Event(), event.getAuthentication());

        } else if (applicationEvent instanceof AuthenticationSuccessEvent || applicationEvent instanceof InteractiveAuthenticationSuccessEvent) {

            // STE2: High Number of Logins Across The Site
            AbstractAuthenticationEvent event = (AbstractAuthenticationEvent)applicationEvent;
            eventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointSTE2Event(), event.getAuthentication());

            //TODO
        } else if (applicationEvent instanceof SessionDestroyedEvent) {

            SessionDestroyedEvent event = (SessionDestroyedEvent)applicationEvent;

            List<SecurityContext> securityContexts = event.getSecurityContexts();
            if(securityContexts == null || securityContexts.size() == 0) {
                // if there are no destroyed contexts, ignore
                return;
            }

            // get first context
            for (SecurityContext securityContext : securityContexts) {
                Authentication authentication = securityContext.getAuthentication();
                eventPublisher.publishDetectionPointEvent(new AppSesnorDetectionPointSTE1Event(),authentication );
            }


        }
    }


}