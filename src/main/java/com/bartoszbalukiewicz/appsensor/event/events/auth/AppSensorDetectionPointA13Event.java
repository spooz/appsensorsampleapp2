package com.bartoszbalukiewicz.appsensor.event.events.auth;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 20.11.2016.
 */
public class AppSensorDetectionPointA13Event extends AppSensorDetectionPointEvent {

    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.AUTHENTICATION, "AE13");
    }
}
