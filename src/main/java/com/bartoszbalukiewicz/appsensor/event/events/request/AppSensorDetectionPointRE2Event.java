package com.bartoszbalukiewicz.appsensor.event.events.request;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 12.11.2016.
 */
public class AppSensorDetectionPointRE2Event extends AppSensorDetectionPointEvent {
    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.REQUEST, "RE4");
    }
}
