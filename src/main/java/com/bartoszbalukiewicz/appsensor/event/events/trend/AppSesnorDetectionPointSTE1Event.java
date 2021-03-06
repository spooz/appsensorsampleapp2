package com.bartoszbalukiewicz.appsensor.event.events.trend;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 11.10.2016.
 */
public class AppSesnorDetectionPointSTE1Event extends AppSensorDetectionPointEvent {

    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.SYSTEM_TREND, "STE1");
    }
}
