package com.bartoszbalukiewicz.appsensor.event.events.input;

import com.bartoszbalukiewicz.appsensor.event.events.AppSensorDetectionPointEvent;
import org.owasp.appsensor.core.DetectionPoint;

/**
 * Created by Bartek on 08.11.2016.
 */
public class AppSensorDetectionPointIE1Event  extends AppSensorDetectionPointEvent {
    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.INPUT_VALIDATION, "IE1");
    }
}
