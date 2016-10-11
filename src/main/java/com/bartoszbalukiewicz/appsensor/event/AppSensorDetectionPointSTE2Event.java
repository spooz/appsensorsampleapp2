package com.bartoszbalukiewicz.appsensor.event;

import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 11.10.2016.
 */
public class AppSensorDetectionPointSTE2Event extends AppSensorDetectionPointEvent {
    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.SYSTEM_TREND, "STE2");
    }
}
