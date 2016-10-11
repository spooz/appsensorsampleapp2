package com.bartoszbalukiewicz.appsensor.security.event;

import com.bartoszbalukiewicz.appsensor.event.AppSensorDetectionPointAE3Event;
import com.bartoszbalukiewicz.appsensor.event.AppSensorDetectionPointEvent;
import com.bartoszbalukiewicz.appsensor.event.AppSensorDetectionPointSTE2Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import org.owasp.appsensor.core.*;
import org.owasp.appsensor.event.RestEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

/**
 * Created by postgres on 2016-09-27.
 */
@Component
public class SpringSecurityEventListener  {

    @Autowired
    private transient IPAddress locator;

    @Autowired
    private AppSensorClient appSensorClient;

    @Autowired
    private RestEventManager ids;


    @Autowired
    private AppSensorDetectionPointEventPublisher eventPublisher;

    private static String cachedIp = null;



    @Async
    @EventListener
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        // process event
        //TODO TEST
        if (applicationEvent instanceof AbstractAuthenticationFailureEvent) {

            AbstractAuthenticationFailureEvent event = (AbstractAuthenticationFailureEvent)applicationEvent;
            eventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointAE3Event(), event.getAuthentication());

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

            Collection<String> userNamesSeen = new HashSet<>();

            // get first context
            for (SecurityContext securityContext : securityContexts) {

                Authentication authentication = securityContext.getAuthentication();
                IPAddress userAddress = getUserIp(authentication);

                String userName = getUserName(authentication);

                if(userNamesSeen.contains(userName)) {
                    // if we've already created the event for this user, skip
                    continue;
                }

                User user = new User(userName, userAddress);
                // STE1: High Number of Logouts Across The Site
                DetectionPoint detectionPoint = new DetectionPoint(DetectionPoint.Category.SYSTEM_TREND, "STE1");
                ids.addEvent(new Event(user, detectionPoint, getDetectionSystem()));

                userNamesSeen.add(userName);
            }


        }
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

    private DetectionSystem getDetectionSystem() {
        return new DetectionSystem(
                appSensorClient.getConfiguration().getServerConnection().getClientApplicationIdentificationHeaderValue(),
                locator.fromString(getApplicationIp()));
    }

    private String getApplicationIp() {
        return "192.168.1.1";
       /* if (cachedIp != null) {
            return cachedIp;
        }

        String ip = null;

        try {

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();

                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    cachedIp = ip;
                }
            }

        } catch (SocketException e) {
            // ignore this exception
        }

        return ip;*/
    }

}