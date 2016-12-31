package com.bartoszbalukiewicz.appsensor.event.events.trend;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by b.balukiewicz on 19.12.2016.
 */
public class AppSensorDetectionPointSTE4Event extends AppSensorDetectionPointEvent {

    @Override
    public DetectionPoint getDetectionPoint() {
       return new DetectionPoint(DetectionPoint.Category.SYSTEM_TREND, "STE4");
    }
}
