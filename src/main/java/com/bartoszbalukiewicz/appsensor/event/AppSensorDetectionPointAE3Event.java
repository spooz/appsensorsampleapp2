package com.bartoszbalukiewicz.appsensor.event;

import org.owasp.appsensor.core.DetectionPoint;
import org.owasp.appsensor.core.User;

/**
 * Created by Bartek on 11.10.2016.
 */
public class AppSensorDetectionPointAE3Event extends AppSensorDetectionPointEvent {

    @Override
    public DetectionPoint getDetectionPoint() {
        return new DetectionPoint(DetectionPoint.Category.AUTHENTICATION, "AE3");
    }


}
