package com.bartoszbalukiewicz.appsensor.event.events.access;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 12.11.2016.
 */
public class AppSensorDetectionPointACE3Event extends AppSensorDetectionPointEvent {

    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.ACCESS_CONTROL, "ACE3");
    }
}
