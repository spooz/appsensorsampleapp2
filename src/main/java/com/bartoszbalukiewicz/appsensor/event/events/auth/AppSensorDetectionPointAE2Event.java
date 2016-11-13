package com.bartoszbalukiewicz.appsensor.event.events.auth;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 13.11.2016.
 */
public class AppSensorDetectionPointAE2Event extends AppSensorDetectionPointEvent {

    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.AUTHENTICATION, "AE2");
    }
}
