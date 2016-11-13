package com.bartoszbalukiewicz.appsensor.event.listener;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.DetectionSystem;
import org.owasp.appsensor.core.Event;
import org.owasp.appsensor.core.IPAddress;
import org.owasp.appsensor.event.RestEventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Bartek on 10.10.2016.
 */
@Component
public class AppSensorDetectionPointEventListenerImpl implements AppSensorDetectionPointEventListener {

    @Autowired
    private AppSensorClient appSensorClient;

    @Autowired
    private RestEventManager restEventManager;

    @Autowired
    private transient IPAddress locator;

    @Override
    @Async
    @EventListener
    public void handleDetectionPointEvent(AppSensorDetectionPointEvent event) {
        restEventManager.addEvent(new Event(event.getAppSensorUser(), event.getDetectionPoint(), getDetectionSystem()));
    }

    //TODO: cache
    private DetectionSystem getDetectionSystem() {
        return new DetectionSystem(
                appSensorClient.getConfiguration().getServerConnection().getClientApplicationIdentificationHeaderValue(),
                locator.fromString(getApplicationIp()));
    }

    //TODO
    private String getApplicationIp() {
        return "192.168.1.1";
    }


}
